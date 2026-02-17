class DummyClass_35752 {
  @Test
  public void testRouterOneServerDown() throws Exception {
    // Bring down defaultServer1
    defaultServer1.cancelRegistration();

    testSync(25);
    Assert.assertEquals(0, defaultServer1.getNumRequests());
    Assert.assertTrue(defaultServer2.getNumRequests() > 0);

    defaultServer1.registerServer();
  }

}