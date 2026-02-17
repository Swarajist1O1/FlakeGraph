class DummyClass_30960 {
  @Test
  public void shouldSerializePOJOAfterReset() {
    // given
    final POJOArray pojo = new POJOArray();
    pojo.simpleArray().add().setLongProp(124);
    pojo.reset();

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