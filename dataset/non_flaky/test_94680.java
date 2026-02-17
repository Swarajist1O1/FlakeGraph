class DummyClass_94680 {
  @Test public void interruptReadingResponseBody() throws Exception {
      @Override public void run() {
        try {
          sleep(delayMillis);
          toInterrupt.interrupt();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

}