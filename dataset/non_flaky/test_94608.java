class DummyClass_94608 {
  @Test public void getHeadersRetainsCached200LevelWarnings() throws Exception {
  public void assertCookies(HttpUrl url, String... expectedCookies) throws Exception {
    List<String> actualCookies = new ArrayList<>();
    for (HttpCookie cookie : cookieManager.getCookieStore().get(url.uri())) {
      actualCookies.add(cookie.toString());
    }
    assertEquals(Arrays.asList(expectedCookies), actualCookies);
  }

}