class DummyClass_91418 {
    @TestLogging("org.elasticsearch.xpack.security.authc:DEBUG")
    public void testExpiredTokensDeletedAfterExpiration() throws Exception {
        final Client client = client().filterWithHeader(Collections.singletonMap("Authorization",
                UsernamePasswordToken.basicAuthHeaderValue(SecuritySettingsSource.TEST_SUPERUSER,
                        SecuritySettingsSourceField.TEST_PASSWORD_SECURE_STRING)));
        SecurityClient securityClient = new SecurityClient(client);
        CreateTokenResponse response = securityClient.prepareCreateToken()
                .setGrantType("password")
                .setUsername(SecuritySettingsSource.TEST_USER_NAME)
                .setPassword(new SecureString(SecuritySettingsSourceField.TEST_PASSWORD.toCharArray()))
                .get();

        Instant created = Instant.now();

        InvalidateTokenResponse invalidateResponse = securityClient
                .prepareInvalidateToken(response.getTokenString())
                .setType(InvalidateTokenRequest.Type.ACCESS_TOKEN)
                .get();
        assertTrue(invalidateResponse.isCreated());
        AtomicReference<String> docId = new AtomicReference<>();
        assertBusy(() -> {
            SearchResponse searchResponse = client.prepareSearch(SecurityIndexManager.SECURITY_INDEX_NAME)
                    .setSource(SearchSourceBuilder.searchSource()
                            .query(QueryBuilders.termQuery("doc_type", TokenService.INVALIDATED_TOKEN_DOC_TYPE)))
                    .setSize(1)
                    .setTerminateAfter(1)
                    .get();
            assertThat(searchResponse.getHits().getTotalHits(), equalTo(1L));
            docId.set(searchResponse.getHits().getAt(0).getId());
        });

        // hack doc to modify the time to the day before
        Instant dayBefore = created.minus(1L, ChronoUnit.DAYS);
        assertTrue(Instant.now().isAfter(dayBefore));
        client.prepareUpdate(SecurityIndexManager.SECURITY_INDEX_NAME, "doc", docId.get())
                .setDoc("expiration_time", dayBefore.toEpochMilli())
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .get();

        AtomicBoolean deleteTriggered = new AtomicBoolean(false);
        assertBusy(() -> {
            if (deleteTriggered.compareAndSet(false, true)) {
                // invalidate a invalid token... doesn't matter that it is bad... we just want this action to trigger the deletion
                try {
                    securityClient.prepareInvalidateToken("fooobar")
                            .setType(randomFrom(InvalidateTokenRequest.Type.values()))
                            .execute()
                            .actionGet();
                } catch (ElasticsearchSecurityException e) {
                    assertEquals("token malformed", e.getMessage());
                }
            }
            client.admin().indices().prepareRefresh(SecurityIndexManager.SECURITY_INDEX_NAME).get();
            SearchResponse searchResponse = client.prepareSearch(SecurityIndexManager.SECURITY_INDEX_NAME)
                    .setSource(SearchSourceBuilder.searchSource()
                            .query(QueryBuilders.termQuery("doc_type", TokenService.INVALIDATED_TOKEN_DOC_TYPE)))
                    .setSize(0)
                    .setTerminateAfter(1)
                    .get();
            assertThat(searchResponse.getHits().getTotalHits(), equalTo(0L));
        }, 30, TimeUnit.SECONDS);
    }

}