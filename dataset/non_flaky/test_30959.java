class DummyClass_30959 {
  @Test
  public void shouldSerializePOJOWithEmptyArray() {
    // given
    final POJOArray pojo = new POJOArray();

    final int writeLength = pojo.getLength();

    // when
    final UnsafeBuffer resultBuffer = new UnsafeBuffer(new byte[writeLength]);
    pojo.write(resultBuffer, 0);

    // then
    final Map<String, Object> msgPackMap =
        MsgPackUtil.asMap(resultBuffer, 0, resultBuffer.capacity());
    assertThat(msgPackMap).containsOnly(entry("simpleArray", "[]"));
  }

}