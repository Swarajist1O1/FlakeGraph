class DummyClass_30986 {
  @Test
  public void shouldSerializePOJO() {
    // given
    final POJO pojo = new POJO();
    pojo.setEnum(POJOEnum.BAR);
    pojo.setLong(456456L);
    pojo.setInt(123);
    pojo.setString(BUF1);
    pojo.setBinary(BUF2);
    pojo.setPacked(MSGPACK_BUF1);

    pojo.nestedObject().setLong(24L);

    final int writeLength = pojo.getLength();

    // when
    final UnsafeBuffer resultBuffer = new UnsafeBuffer(new byte[writeLength]);
    pojo.write(resultBuffer, 0);

    // then
    final Map<String, Object> msgPackMap =
        MsgPackUtil.asMap(resultBuffer, 0, resultBuffer.capacity());
    assertThat(msgPackMap).hasSize(7);
    assertThat(msgPackMap)
        .contains(
            entry("enumProp", POJOEnum.BAR.toString()),
            entry("longProp", 456456L),
            entry("intProp", 123L),
            entry("stringProp", "foo"),
            entry("binaryProp", BUF2.byteArray()));

    final Map<String, Object> packedProp = (Map<String, Object>) msgPackMap.get("packedProp");
    assertThat(packedProp).containsExactly(entry("foo", 123123L));

    final Map<String, Object> objectProp = (Map<String, Object>) msgPackMap.get("objectProp");
    assertThat(objectProp).containsExactly(entry("foo", 24L));
  }

}