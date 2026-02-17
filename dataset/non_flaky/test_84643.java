class DummyClass_84643 {
    @Test
    public void testEnforceAuthenticationNewBehaviour() throws Exception {
        Map<String, String> prop = new HashMap<>();
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "true");
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_SCHEMES), "digest");
        //digest auth provider is started by default, so no need to
        //prop.put("authProvider.1", DigestAuthenticationProvider.class.getName());
        startServer(prop);
        testEnforceAuthNewBehaviour(false);
    }

}