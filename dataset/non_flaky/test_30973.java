class DummyClass_30973 {
  @Test
  public void shouldDeserializePOJOWithUndeclaredProperties() {
    // given
    final MinimalPOJO pojo = new MinimalPOJO();

    // when
    pojo.wrap(MSG_PACK);

    // then
    assertThat(pojo.getLongProp()).isEqualTo(123L);
  }

}