class DummyClass_94636 {
  @Test public void applicationInterceptorReturnsNull() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        chain.proceed(chain.request());
        return null;
      }

}