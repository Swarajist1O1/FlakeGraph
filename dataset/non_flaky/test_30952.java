class DummyClass_30952 {
  @Test
  public void shouldGetZeroSubscriptionHashCodeIfEmpty() {
    assertThat(getSubscriptionHashCode(new UnsafeBuffer())).isEqualTo(0);
  }

}