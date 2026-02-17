class DummyClass_35754 {
  @Test
  public void testHostForward() throws Exception {
    // Test defaultService
    HttpResponse response = get(resolveURI(String.format("%s/%s", "/v1/ping", "sync")));
    Assert.assertEquals(HttpResponseStatus.OK.code(), response.getStatusLine().getStatusCode());
    Assert.assertEquals(APP_FABRIC_SERVICE, EntityUtils.toString(response.getEntity()));
  }

}