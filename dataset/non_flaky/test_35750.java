class DummyClass_35750 {
  @Test
  public void testRouterSync() throws Exception {
    testSync(25);
    // sticky endpoint strategy used so the sum should be 25
    Assert.assertEquals(25, defaultServer1.getNumRequests() + defaultServer2.getNumRequests());
  }

}