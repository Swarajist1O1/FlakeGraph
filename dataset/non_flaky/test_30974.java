class DummyClass_30974 {
  @Test
  public void shouldIncludeUndeclaredPropertiesInLengthEstimation() {
    // given
    final MinimalPOJO pojo = new MinimalPOJO();
    pojo.wrap(MSG_PACK);

    // when
    final long writeLength = pojo.getLength();

    // then
    assertThat(writeLength).isEqualTo(MSG_PACK.capacity());
  }

}