class DummyClass_35660 {
  @Test
  public void testSpark2Service() throws Exception {
    ApplicationManager applicationManager = deploy(NamespaceId.DEFAULT, Spark2TestApp.class);
    SparkManager manager = applicationManager.getSparkManager(ScalaSparkServiceProgram.class.getSimpleName()).start();

    URL url = manager.getServiceURL(5, TimeUnit.MINUTES);
    Assert.assertNotNull(url);

    // GET request to sum n numbers.
    URL sumURL = url.toURI().resolve("sum?n=" + Joiner.on("&n=").join(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).toURL();
    HttpURLConnection urlConn = (HttpURLConnection) sumURL.openConnection();
    Assert.assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
    try (InputStream is = urlConn.getInputStream()) {
      Assert.assertEquals(55, Integer.parseInt(new String(ByteStreams.toByteArray(is), StandardCharsets.UTF_8)));
    }
  }

}