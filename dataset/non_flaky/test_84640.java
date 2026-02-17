class DummyClass_84640 {
    @Test
    public void testEnforceAuthenticationOldBehaviourWithNetty() throws Exception {
        Map<String, String> prop = new HashMap<>();
        //setting property false should give the same behaviour as when property is not set
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "false");
        prop.put("serverCnxnFactory", "org.apache.zookeeper.server.NettyServerCnxnFactory");
        startServer(prop);
        testEnforceAuthOldBehaviour(true);
    }

}