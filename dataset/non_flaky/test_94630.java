class DummyClass_94630 {
  @Test public void applicationInterceptorsCanMakeMultipleRequestsToServer() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            Response response1 = chain.proceed(chain.request());
            response1.body().close();
            return chain.proceed(chain.request());
          }

}