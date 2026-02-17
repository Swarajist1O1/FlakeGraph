class DummyClass_89304 {
  @Test
  public void testScheduledExecutorSchedulingProvider() {
    // Test that the monitor is scheduled by the ScheduledExecutorSchedulingProvider
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    // notifyingMonitor.monitor() should be called repeatedly.
    final CountDownLatch wasCalledLatch = new CountDownLatch(3);

    final Monitor notifyingMonitor = new Monitor() {
      @Override
      public void monitor() {
        wasCalledLatch.countDown();
      }

}