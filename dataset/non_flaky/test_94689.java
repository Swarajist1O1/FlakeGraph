class DummyClass_94689 {
  @Test public void getHeadersRetainsCached200LevelWarnings() throws Exception {
  public void assertCookies(URL url, String... expectedCookies) throws Exception {
    List<String> actualCookies = new ArrayList<>();
    for (HttpCookie cookie : cookieManager.getCookieStore().get(url.toURI())) {
      actualCookies.add(cookie.toString());
    }
    assertEquals(Arrays.asList(expectedCookies), actualCookies);
  }

}