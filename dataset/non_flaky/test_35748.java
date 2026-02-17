class DummyClass_35748 {
  @Test
  public void testEmptyAnnounceAddressURLsConfig() throws Exception {
    HttpRouterService routerService = new AuthServerAnnounceTest.HttpRouterService(HOSTNAME, DISCOVERY_SERVICE);
    routerService.startUp();
    try {
      Assert.assertEquals(Collections.EMPTY_LIST, getAuthURI(routerService));
    } finally {
      routerService.shutDown();
    }
  }

}