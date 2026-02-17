class DummyClass_94623 {
  @Test public void networkInterceptorsHaveConnectionAccess() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Connection connection = chain.connection();
        assertNotNull(connection);
        return chain.proceed(chain.request());
      }

}