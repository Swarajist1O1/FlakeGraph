class DummyClass_94642 {
  @Test public void idleCallbackInvokedWhenIdle() throws InterruptedException {
      @Override public void run() {
        idle.set(true);
      }

}