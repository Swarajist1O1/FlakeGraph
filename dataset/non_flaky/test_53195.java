class DummyClass_53195 {
    @Test
    public void testQRCodeScreenAfterRegistrationDeletion() throws Exception {
        // register mfa for user and logout
        testQRCodeScreen();
        webDriver.get(zoneUrl + "/logout.do");

        // retrieve user id and delete mfa registration
        RestTemplate client = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + zoneAdminToken);
        headers.add("X-Identity-Zone-Id", mfaZone.getId());
        headers.add("Content-Type", "application/json");
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("filter","userName eq \""+username+"\"");
        ResponseEntity<Map> exchange = client.exchange(serverRunning.getUrl("/Users?attributes=id&filter={filter}"), HttpMethod.GET, new HttpEntity<Void>(
            headers), Map.class, uriParams);
        String userId = (String) ((Map)((java.util.List) exchange.getBody().get("resources")).get(0)).get("id");

        client.exchange(serverRunning.getUrl("/Users/{userId}/mfa"), HttpMethod.DELETE, new HttpEntity<Void>(
            headers), Map.class, userId);

        // user login should end up at mfa registration page
        performLogin(username);
        assertEquals(zoneUrl + "/login/mfa/register", webDriver.getCurrentUrl());
    }

}