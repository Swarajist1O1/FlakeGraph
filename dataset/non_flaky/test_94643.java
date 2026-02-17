class DummyClass_94643 {
  @Test public void clonedInterceptorsListsAreIndependent() throws Exception {
      @Override public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request());
      }

}