class DummyClass_30961 {
  @Test
  public void shouldSerializePOJOWithDefaultValues() {
    // given
    final POJOArray pojo = new POJOArray();
    final ValueArray<MinimalPOJO> iterator1 = pojo.simpleArray();
    iterator1.add().setLongProp(123L);

    final int writeLength = pojo.getLength();

    // when
    final UnsafeBuffer resultBuffer = new UnsafeBuffer(new byte[writeLength]);
    pojo.write(resultBuffer, 0);

    // then
    final Map<String, Object> msgPackMap =
        MsgPackUtil.asMap(resultBuffer, 0, resultBuffer.capacity());
    assertThat(msgPackMap).containsOnly(entry("simpleArray", "[{longProp=123}]"));
  }

}