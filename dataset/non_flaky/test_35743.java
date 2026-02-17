class DummyClass_35743 {
  @Test
  public void testAuditLog() throws IOException {
    HttpURLConnection urlConn = createURLConnection("/get", HttpMethod.GET);
    Assert.assertEquals(200, urlConn.getResponseCode());
    urlConn.getInputStream().close();

    urlConn = createURLConnection("/put", HttpMethod.PUT);
    urlConn.getOutputStream().write("Test Put".getBytes(StandardCharsets.UTF_8));
    Assert.assertEquals(200, urlConn.getResponseCode());
    Assert.assertEquals("Test Put", new String(ByteStreams.toByteArray(urlConn.getInputStream()), "UTF-8"));
    urlConn.getInputStream().close();

    urlConn = createURLConnection("/post", HttpMethod.POST);
    urlConn.getOutputStream().write("Test Post".getBytes(StandardCharsets.UTF_8));
    Assert.assertEquals(200, urlConn.getResponseCode());
    Assert.assertEquals("Test Post", new String(ByteStreams.toByteArray(urlConn.getInputStream()), "UTF-8"));
    urlConn.getInputStream().close();

    urlConn = createURLConnection("/postHeaders", HttpMethod.POST);
    urlConn.setRequestProperty("user-id", "cdap");
    urlConn.getOutputStream().write("Post Headers".getBytes(StandardCharsets.UTF_8));
    Assert.assertEquals(200, urlConn.getResponseCode());
    Assert.assertEquals("Post Headers", new String(ByteStreams.toByteArray(urlConn.getInputStream()), "UTF-8"));
    urlConn.getInputStream().close();

    List<String> loggedMessages = TestLogAppender.INSTANCE.getLoggedMessages();
    Assert.assertEquals(4, loggedMessages.size());

    Assert.assertTrue(loggedMessages.get(0).endsWith("\"GET /get HTTP/1.1\" - - 200 0 -"));
    Assert.assertTrue(loggedMessages.get(1).endsWith("\"PUT /put HTTP/1.1\" - Test Put 200 8 -"));
    Assert.assertTrue(loggedMessages.get(2).endsWith("\"POST /post HTTP/1.1\" - Test Post 200 9 Test Post"));
    Assert.assertTrue(
      loggedMessages.get(3).endsWith("\"POST /postHeaders HTTP/1.1\" {user-id=cdap} Post Headers 200 12 Post Headers"));
  }

}