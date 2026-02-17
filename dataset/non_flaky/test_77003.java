class DummyClass_77003 {
  @Test
  public void clearResourceTest() throws Exception {
    final ShuffleWriteClient shuffleWriteClient =
        ShuffleClientFactory.getInstance().createShuffleWriteClient(
            "GRPC", 2, 10000L, 4);
    shuffleWriteClient.registerCoordinators("127.0.0.1:19999");
    shuffleWriteClient.registerShuffle(
        new ShuffleServerInfo("127.0.0.1-20001", "127.0.0.1", 20001),
        "clearResourceTest1",
        0,
        Lists.newArrayList(new PartitionRange(0, 1)));

    shuffleWriteClient.sendAppHeartbeat("clearResourceTest1", 1000L);
    shuffleWriteClient.sendAppHeartbeat("clearResourceTest2", 1000L);

    RssRegisterShuffleRequest rrsr = new RssRegisterShuffleRequest("clearResourceTest1", 0,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);
    rrsr = new RssRegisterShuffleRequest("clearResourceTest2", 0,
        Lists.newArrayList(new PartitionRange(0, 1)));
    shuffleServerClient.registerShuffle(rrsr);
    assertEquals(Sets.newHashSet("clearResourceTest1", "clearResourceTest2"),
        shuffleServers.get(0).getShuffleTaskManager().getAppIds().keySet());

    // Thread will keep refresh clearResourceTest1 in coordinator
    Thread t = new Thread(() -> {
      int i = 0;
      while (i < 20) {
        shuffleWriteClient.sendAppHeartbeat("clearResourceTest1", 1000L);
        i++;
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          return;
        }
      }
    });
    t.start();

    // Heartbeat is sent to coordinator too]
    Thread.sleep(3000);
    shuffleServerClient.registerShuffle(new RssRegisterShuffleRequest("clearResourceTest1", 0,
        Lists.newArrayList(new PartitionRange(0, 1))));
    assertEquals(Sets.newHashSet("clearResourceTest1"),
        coordinators.get(0).getApplicationManager().getAppIds());
    // clearResourceTest2 will be removed because of rss.server.app.expired.withoutHeartbeat
    Thread.sleep(2000);
    assertEquals(Sets.newHashSet("clearResourceTest1"),
        shuffleServers.get(0).getShuffleTaskManager().getAppIds().keySet());

    // clearResourceTest1 will be removed because of rss.server.app.expired.withoutHeartbeat
    t.interrupt();
    Thread.sleep(8000);
    assertEquals(0, shuffleServers.get(0).getShuffleTaskManager().getAppIds().size());

  }

}