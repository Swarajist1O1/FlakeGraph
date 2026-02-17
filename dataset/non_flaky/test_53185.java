class DummyClass_53185 {
    @Test
    public void testDefaultClaims() {
        assertEquals("issuer", defaultConfig.getIssuer());
        assertEquals("/uaa/oauth/authorize", defaultConfig.getAuthUrl());
        assertEquals("/uaa/oauth/token", defaultConfig.getTokenUrl());
        assertArrayEquals(new String[]{"client_secret_basic", "client_secret_post"}, defaultConfig.getTokenAMR());
        assertArrayEquals(new String[]{"RS256", "HS256"}, defaultConfig.getTokenEndpointAuthSigningValues());
        assertEquals("/uaa/userinfo", defaultConfig.getUserInfoUrl());
        assertEquals("/uaa/token_keys", defaultConfig.getJwksUri());
        assertArrayEquals(new String[]{"openid", "profile", "email", "phone", "roles", "user_attributes"}, defaultConfig.getScopes());
        assertArrayEquals(new String[]{"code", "code id_token", "id_token", "token id_token"}, defaultConfig.getResponseTypes());
        assertArrayEquals(new String[]{"public"}, defaultConfig.getSubjectTypesSupported());
        assertArrayEquals(new String[]{"RS256", "HS256"}, defaultConfig.getIdTokenSigningAlgValues());
        assertArrayEquals(new String[]{"none"}, defaultConfig.getRequestObjectSigningAlgValues());
        assertArrayEquals(new String[]{"normal"}, defaultConfig.getClaimTypesSupported());
        assertArrayEquals(
            new String[]{
                "sub", "user_name", "origin", "iss", "auth_time",
                "amr", "acr", "client_id", "aud", "zid", "grant_type",
                "user_id", "azp", "scope", "exp", "iat", "jti", "rev_sig",
                "cid", "given_name", "family_name", "phone_number", "email"},
            defaultConfig.getClaimsSupported()
        );
        assertFalse(defaultConfig.isClaimsParameterSupported());
        assertEquals("http://docs.cloudfoundry.org/api/uaa/", defaultConfig.getServiceDocumentation());
        assertArrayEquals(new String[]{"en-US"}, defaultConfig.getUiLocalesSupported());
    }

}