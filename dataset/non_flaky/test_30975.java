class DummyClass_30975 {
  @Test
  public void shouldSerializeUndeclaredProperties() {
    // given
    final MinimalPOJO pojo = new MinimalPOJO();
    pojo.wrap(MSG_PACK);

    final MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[pojo.getLength()]);

    // when
    pojo.write(writeBuffer, 0);

    // then
    final Map<String, Object> serialized = asMap(writeBuffer, 0, writeBuffer.capacity());

    assertThat(serialized).hasSize(2);
    assertThat(serialized).contains(entry("longProp", 123L), entry("undeclaredProp", 456L));
  }

}