class DummyClass_78274 {
  @Test
  public void testFromStringIntervalWindow() {
    assertStringKeyRoundTrips(
        intervalCoder, StateNamespaces.window(intervalCoder, intervalWindow(1000, 8000)));
    assertStringKeyRoundTrips(
        intervalCoder, StateNamespaces.window(intervalCoder, intervalWindow(1000, 8000)));

    assertStringKeyRoundTrips(
        intervalCoder,
        StateNamespaces.windowAndTrigger(intervalCoder, intervalWindow(1000, 8000), 18));
    assertStringKeyRoundTrips(
        intervalCoder,
        StateNamespaces.windowAndTrigger(intervalCoder, intervalWindow(1000, 8000), 19));
    assertStringKeyRoundTrips(
        intervalCoder,
        StateNamespaces.windowAndTrigger(intervalCoder, intervalWindow(2000, 8000), 19));
  }

}