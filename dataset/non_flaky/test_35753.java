class DummyClass_35753 {
  @Test
  public void testRouterAllServersDown() throws Exception {
    // Bring down all servers
    defaultServer1.cancelRegistration();
    defaultServer2.cancelRegistration();

    testSyncServiceUnavailable();
    Assert.assertEquals(0, defaultServer1.getNumRequests());
    Assert.assertEquals(0, defaultServer2.getNumRequests());

    defaultServer1.registerServer();
    defaultServer2.registerServer();
  }

}