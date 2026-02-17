class DummyClass_30953 {
  @Test
  public void shouldGetPartitionIdForCorrelationKey() {
    assertThat(getSubscriptionPartitionId(wrapString("a"), 10)).isEqualTo(7 + START_PARTITION_ID);
    assertThat(getSubscriptionPartitionId(wrapString("b"), 3)).isEqualTo(2 + START_PARTITION_ID);
    assertThat(getSubscriptionPartitionId(wrapString("c"), 11)).isEqualTo(0 + START_PARTITION_ID);
    assertThat(getSubscriptionPartitionId(wrapString("foobar"), 100))
        .isEqualTo(63 + START_PARTITION_ID);
  }

}