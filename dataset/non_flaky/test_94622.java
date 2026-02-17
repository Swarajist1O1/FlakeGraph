class DummyClass_94622 {
  @Test public void networkInterceptorsCannotChangeServerAddress() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        Address address = chain.connection().route().address();
        String sameHost = address.url().host();
        int differentPort = address.url().port() + 1;
        return chain.proceed(chain.request().newBuilder()
            .url(HttpUrl.parse("http://" + sameHost + ":" + differentPort + "/"))
            .build());
      }

}