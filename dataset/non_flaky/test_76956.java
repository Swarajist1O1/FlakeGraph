class DummyClass_76956 {
  @Test
  public void jettyServerStartTest() throws Exception {
    try {
      RssBaseConf conf = new RssBaseConf();
      conf.setString("rss.jetty.http.port", "9527");
      JettyServer jettyServer1 = new JettyServer(conf);
      JettyServer jettyServer2 = new JettyServer(conf);
      jettyServer1.start();

      ExitUtils.disableSystemExit();
      final String expectMessage = "Fail to start jetty http server";
      final int expectStatus = 1;
      try {
        jettyServer2.start();
      } catch (Exception e) {
        assertEquals(expectMessage, e.getMessage());
        assertEquals(expectStatus, ((ExitException) e).getStatus());
      }

      final Thread t = new Thread(null, () -> {
        throw new AssertionError("TestUncaughtException");
      }, "testThread");
      t.start();
      t.join();
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

}