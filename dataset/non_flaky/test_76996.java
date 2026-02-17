class DummyClass_76996 {
  @Test
  public void shuffleServerHeartbeatTest() throws Exception {
    CoordinatorTestUtils.waitForRegister(coordinatorClient, 2);
    shuffleServers.get(0).stopServer();
    Thread.sleep(5000);
    SimpleClusterManager scm = (SimpleClusterManager) coordinators.get(0).getClusterManager();
    List<ServerNode> nodes = scm.getServerList(Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    assertEquals(1, nodes.size());
    ServerNode node = nodes.get(0);
    assertTrue(node.getTags().contains(Constants.SHUFFLE_SERVER_VERSION));
    assertTrue(scm.getTagToNodes().get(Constants.SHUFFLE_SERVER_VERSION).contains(node));
    ShuffleServerConf shuffleServerConf = shuffleServers.get(0).getShuffleServerConf();
    shuffleServerConf.setInteger("rss.rpc.server.port", SHUFFLE_SERVER_PORT + 2);
    shuffleServerConf.setInteger("rss.jetty.http.port", 18082);
    ShuffleServer ss = new ShuffleServer(shuffleServerConf);
    ss.start();
    shuffleServers.set(0, ss);
    Thread.sleep(3000);
    assertEquals(2, coordinators.get(0).getClusterManager().getNodesNum());
  }

}