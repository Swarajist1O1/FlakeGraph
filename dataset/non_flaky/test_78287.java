class DummyClass_78287 {
  @Test
  public void testCompareByDomain() {
    Instant timestamp = new Instant(100);
    StateNamespace namespace = StateNamespaces.global();

    TimerData eventTimer = TimerData.of(namespace, timestamp, TimeDomain.EVENT_TIME);
    TimerData procTimer = TimerData.of(namespace, timestamp, TimeDomain.PROCESSING_TIME);
    TimerData synchronizedProcTimer =
        TimerData.of(namespace, timestamp, TimeDomain.SYNCHRONIZED_PROCESSING_TIME);

    assertThat(eventTimer, lessThan(procTimer));
    assertThat(eventTimer, lessThan(synchronizedProcTimer));
    assertThat(procTimer, lessThan(synchronizedProcTimer));
  }

}