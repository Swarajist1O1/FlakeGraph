class DummyClass_94638 {
  @Test public void networkInterceptorReturnsConnectionOnEmptyBody() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        assertNotNull(chain.connection());
        return response;
      }

}