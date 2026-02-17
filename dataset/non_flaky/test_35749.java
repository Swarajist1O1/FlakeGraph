class DummyClass_35749 {
  @Test
  public void testAnnounceURLsConfig() throws Exception {
    HttpRouterService routerService = new AuthServerAnnounceTest.HttpRouterService(HOSTNAME, DISCOVERY_SERVICE);
    routerService.cConf.set(Constants.Security.AUTH_SERVER_ANNOUNCE_URLS, ANNOUNCE_URLS);
    routerService.startUp();
    try {
      List<String> expected = Stream.of(ANNOUNCE_URLS.split(","))
        .map(url -> String.format("%s/%s", url, GrantAccessToken.Paths.GET_TOKEN))
        .collect(Collectors.toList());
      Assert.assertEquals(expected, getAuthURI(routerService));
    } finally {
      routerService.shutDown();
    }
  }

}