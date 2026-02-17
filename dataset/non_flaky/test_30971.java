class DummyClass_30971 {
  @Test
  public void shouldAddFirstEntryToSimpleArrayProp() {
    // given
    final POJOArray pojo = new POJOArray();
    final ValueArray<MinimalPOJO> iterator = pojo.simpleArray();

    // when
    iterator.add().setLongProp(741L);

    // then
    final int length = pojo.getLength();
    final UnsafeBuffer resultBuffer = new UnsafeBuffer(new byte[length]);
    pojo.write(resultBuffer, 0);

    final Map<String, Object> msgPackMap =
        MsgPackUtil.asMap(resultBuffer, 0, resultBuffer.capacity());
    assertThat(msgPackMap).containsOnly(entry("simpleArray", "[{longProp=741}]"));
  }

}