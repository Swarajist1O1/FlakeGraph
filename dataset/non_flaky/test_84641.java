class DummyClass_84641 {
    @Test
    public void testServerStartShouldFailWhenEnforceAuthSchemeIsNotConfigured() throws Exception {
        Map<String, String> prop = new HashMap<>();
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "true");
        testServerCannotStart(prop);
    }

}