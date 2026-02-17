class DummyClass_53153 {
    @Test
    public void json_has_expected_properties() throws Exception {
        TokenPolicy tokenPolicy = new TokenPolicy();
        tokenPolicy.setAccessTokenValidity(1234);
        tokenPolicy.setRefreshTokenValidity(9876);
        tokenPolicy.setKeys(Collections.singletonMap("aKeyId", "KeyKeyKey"));

        String json = JsonUtils.writeValueAsString(tokenPolicy);
        Map properties = JsonUtils.readValue(json, Map.class);

        assertNotNull(properties);
        assertEquals(1234, properties.get("accessTokenValidity"));
        assertEquals(9876, properties.get("refreshTokenValidity"));
        assertNotNull(properties.get("keys"));
        Map keys = (Map) properties.get("keys");
        assertNotNull(keys);
        assertEquals(keys.size(), 1);
        assertEquals("KeyKeyKey", ((Map) keys.get("aKeyId")).get("signingKey"));
    }

}