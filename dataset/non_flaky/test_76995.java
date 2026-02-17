class DummyClass_76995 {
  @Test
  public void appHeartbeatTest() throws Exception {
    RssAppHeartBeatResponse response =
        coordinatorClient.sendAppHeartBeat(new RssAppHeartBeatRequest("appHeartbeatTest1", 1000));
    assertEquals(ResponseStatusCode.SUCCESS, response.getStatusCode());
    assertEquals(Sets.newHashSet("appHeartbeatTest1"),
        coordinators.get(0).getApplicationManager().getAppIds());
    coordinatorClient.sendAppHeartBeat(new RssAppHeartBeatRequest("appHeartbeatTest2", 1000));
    assertEquals(Sets.newHashSet("appHeartbeatTest1", "appHeartbeatTest2"),
        coordinators.get(0).getApplicationManager().getAppIds());
    int retry = 0;
    while (retry < 5) {
      coordinatorClient.sendAppHeartBeat(new RssAppHeartBeatRequest("appHeartbeatTest1", 1000));
      retry++;
      Thread.sleep(1000);
    }
    // appHeartbeatTest2 was removed because of expired
    assertEquals(Sets.newHashSet("appHeartbeatTest1"),
        coordinators.get(0).getApplicationManager().getAppIds());
  }

}