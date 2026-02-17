class DummyClass_84642 {
    @Test
    public void testServerStartShouldFailWhenAuthProviderIsNotConfigured() throws Exception {
        Map<String, String> prop = new HashMap<>();
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "true");
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_SCHEMES), "sasl");
        testServerCannotStart(prop);
    }

}