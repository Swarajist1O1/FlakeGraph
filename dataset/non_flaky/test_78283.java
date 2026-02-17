class DummyClass_78283 {
  @Test
  public void testTimerDataCoder() throws Exception {
    CoderProperties.coderDecodeEncodeEqual(
        TimerDataCoder.of(GlobalWindow.Coder.INSTANCE),
        TimerData.of(
            "arbitrary-id", StateNamespaces.global(), new Instant(0), TimeDomain.EVENT_TIME));

    Coder<IntervalWindow> windowCoder = IntervalWindow.getCoder();
    CoderProperties.coderDecodeEncodeEqual(
        TimerDataCoder.of(windowCoder),
        TimerData.of(
            "another-id",
            StateNamespaces.window(
                windowCoder, new IntervalWindow(new Instant(0), new Instant(100))),
            new Instant(99),
            TimeDomain.PROCESSING_TIME));
  }

}