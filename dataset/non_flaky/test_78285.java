class DummyClass_78285 {
  @Test
  public void testCompareEqual() {
    Instant timestamp = new Instant(100);
    StateNamespace namespace = StateNamespaces.global();
    TimerData timer = TimerData.of("id", namespace, timestamp, TimeDomain.EVENT_TIME);

    assertThat(
        timer, comparesEqualTo(TimerData.of("id", namespace, timestamp, TimeDomain.EVENT_TIME)));
  }

}