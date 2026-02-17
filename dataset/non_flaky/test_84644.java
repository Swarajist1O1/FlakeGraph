class DummyClass_84644 {
    @Test
    public void testEnforceAuthenticationNewBehaviourWithNetty() throws Exception {
        Map<String, String> prop = new HashMap<>();
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "true");
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_SCHEMES), "digest");
        prop.put("serverCnxnFactory", "org.apache.zookeeper.server.NettyServerCnxnFactory");
        startServer(prop);
        testEnforceAuthNewBehaviour(true);
    }

}