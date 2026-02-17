class DummyClass_94619 {
  @Test public void applicationInterceptorsCanShortCircuitResponses() throws Exception {
          @Override public Response intercept(Chain chain) throws IOException {
            return interceptorResponse;
          }

}