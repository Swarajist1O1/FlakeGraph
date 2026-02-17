class DummyClass_78289 {
  @Test
  public void testCompareByTimerId() {
    Instant timestamp = new Instant(100);
    StateNamespace namespace = StateNamespaces.global();

    TimerData id0Timer = TimerData.of("id0", namespace, timestamp, TimeDomain.EVENT_TIME);
    TimerData id1Timer = TimerData.of("id1", namespace, timestamp, TimeDomain.EVENT_TIME);

    assertThat(id0Timer, lessThan(id1Timer));
  }

}