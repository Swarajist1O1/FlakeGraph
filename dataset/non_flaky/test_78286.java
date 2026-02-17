class DummyClass_78286 {
  @Test
  public void testCompareByTimestamp() {
    Instant firstTimestamp = new Instant(100);
    Instant secondTimestamp = new Instant(200);
    StateNamespace namespace = StateNamespaces.global();

    TimerData firstTimer = TimerData.of(namespace, firstTimestamp, TimeDomain.EVENT_TIME);
    TimerData secondTimer = TimerData.of(namespace, secondTimestamp, TimeDomain.EVENT_TIME);

    assertThat(firstTimer, lessThan(secondTimer));
  }

}