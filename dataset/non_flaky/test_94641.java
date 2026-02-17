class DummyClass_94641 {
  @Test public void synchronousCallAccessors() throws Exception {
              @Override public Response intercept(Chain chain) throws IOException {
                try {
                  ready.countDown();
                  waiting.await();
                } catch (InterruptedException e) {
                  throw new AssertionError();
                }
                throw new IOException();
              }

}