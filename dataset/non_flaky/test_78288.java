class DummyClass_78288 {
  @Test
  public void testCompareByNamespace() {
    Instant timestamp = new Instant(100);
    IntervalWindow firstWindow = new IntervalWindow(new Instant(0), timestamp);
    IntervalWindow secondWindow = new IntervalWindow(timestamp, new Instant(200));
    Coder<IntervalWindow> windowCoder = IntervalWindow.getCoder();

    StateNamespace firstWindowNs = StateNamespaces.window(windowCoder, firstWindow);
    StateNamespace secondWindowNs = StateNamespaces.window(windowCoder, secondWindow);

    TimerData secondEventTime = TimerData.of(firstWindowNs, timestamp, TimeDomain.EVENT_TIME);
    TimerData thirdEventTime = TimerData.of(secondWindowNs, timestamp, TimeDomain.EVENT_TIME);

    assertThat(secondEventTime, lessThan(thirdEventTime));
  }

}