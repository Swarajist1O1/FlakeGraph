class DummyClass_94679 {
  @Test public void interruptReadingResponseBody() throws Exception {
      @Override public void run() {
        try {
          sleep(delayMillis);
          connection.disconnect();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

}