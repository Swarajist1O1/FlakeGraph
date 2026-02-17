class DummyClass_94606 {
  @Test
  public void testNetscapeResponse() throws Exception {
    CookieManager cookieManager = new CookieManager(null, ACCEPT_ORIGINAL_SERVER);
    client = client.newBuilder()
        .cookieJar(new JavaNetCookieJar(cookieManager))
        .build();
    MockWebServer server = new MockWebServer();
    server.start();

    HttpUrl urlWithIpAddress = urlWithIpAddress(server, "/path/foo");
    server.enqueue(new MockResponse().addHeader("Set-Cookie: a=android; "
        + "expires=Fri, 31-Dec-9999 23:59:59 GMT; "
        + "path=/path; "
        + "domain=" + urlWithIpAddress.host() + "; "
        + "secure"));
    get(urlWithIpAddress);

    List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
    assertEquals(1, cookies.size());
    HttpCookie cookie = cookies.get(0);
    assertEquals("a", cookie.getName());
    assertEquals("android", cookie.getValue());
    assertEquals(null, cookie.getComment());
    assertEquals(null, cookie.getCommentURL());
    assertEquals(false, cookie.getDiscard());
    assertTrue(cookie.getMaxAge() > 100000000000L);
    assertEquals("/path", cookie.getPath());
    assertEquals(true, cookie.getSecure());
    assertEquals(0, cookie.getVersion());
  }

}