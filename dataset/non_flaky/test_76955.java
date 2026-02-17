class DummyClass_76955 {
  @Test
  public void jettyServerTest() throws FileNotFoundException {
    RssBaseConf conf = new RssBaseConf();
    conf.setString("rss.jetty.http.port", "9527");
    JettyServer jettyServer = new JettyServer(conf);
    Server server = jettyServer.getServer();

    assertEquals(4, server.getBeans().size());
    assertEquals(30000, server.getStopTimeout());
    assertTrue(server.getThreadPool() instanceof ExecutorThreadPool);

    assertEquals(1, server.getConnectors().length);
    assertEquals(server, server.getHandler().getServer());
    assertTrue(server.getConnectors()[0] instanceof ServerConnector);
    ServerConnector connector = (ServerConnector) server.getConnectors()[0];
    assertEquals(9527, connector.getPort());

    assertEquals(1, server.getHandlers().length);
    Handler handler = server.getHandler();
    assertTrue(handler instanceof ServletContextHandler);
  }

}