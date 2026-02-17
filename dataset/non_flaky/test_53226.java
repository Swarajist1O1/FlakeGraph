class DummyClass_53226 {
    @Test
    public void testSimpleSamlPhpLogin() throws Exception {
        Long beforeTest = System.currentTimeMillis();
        testSimpleSamlLogin("/login", "Where to?");
        Long afterTest = System.currentTimeMillis();
        String zoneAdminToken = IntegrationTestUtils.getClientCredentialsToken(serverRunning, "admin", "adminsecret");
        ScimUser user = IntegrationTestUtils.getUser(zoneAdminToken, baseUrl, SAML_ORIGIN, testAccounts.getEmail());
        IntegrationTestUtils.validateUserLastLogon(user, beforeTest, afterTest);
    }

}