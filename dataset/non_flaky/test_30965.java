class DummyClass_30965 {
  @Test
  public void shouldSerializeInbetweenAddedEntry() {
    // given
    final POJOArray pojo = new POJOArray();
    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              encodeSimpleArrayProp(w);
            });

    pojo.wrap(buffer);
    final Iterator<MinimalPOJO> iterator = pojo.simpleArray().iterator();
    iterator.next();
    iterator.next();
    iterator.next();

    // when
    pojo.simpleArrayProp.add().setLongProp(999L);

    // then
    final int writeLength = pojo.getLength();
    final UnsafeBuffer pojoBuffer = new UnsafeBuffer(new byte[writeLength]);
    pojo.write(pojoBuffer, 0);

    final Map<String, Object> msgPackMap = MsgPackUtil.asMap(pojoBuffer, 0, pojoBuffer.capacity());
    assertThat(msgPackMap)
        .containsOnly(
            entry(
                "simpleArray",
                "[{longProp=123}, {longProp=456}, {longProp=789}, {longProp=999}, {longProp=555}, {longProp=777}]"));
  }

}