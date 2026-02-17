class DummyClass_30976 {
  @Test
  public void shouldDropUndeclaredPropertiesOnReset() {
    // given
    final MinimalPOJO pojo = new MinimalPOJO();
    pojo.wrap(MSG_PACK);

    final MutableDirectBuffer writeBuffer = new UnsafeBuffer(new byte[pojo.getLength()]);

    // when
    pojo.reset();

    // then
    pojo.wrap(
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              w.writeString(wrapString("longProp"));
              w.writeInteger(123L);
            }));
    pojo.write(writeBuffer, 0);

    final Map<String, Object> serialized = asMap(writeBuffer, 0, writeBuffer.capacity());
    assertThat(serialized).containsExactly(entry("longProp", 123L));
  }

}