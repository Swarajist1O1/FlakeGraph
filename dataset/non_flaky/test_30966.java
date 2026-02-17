class DummyClass_30966 {
  @Test
  public void shouldDeserializePOJO() {
    // given
    final POJOArray pojo = new POJOArray();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(3);
              encodeSimpleArrayProp(w);

              w.writeString(wrapString("emptyDefaultArray"));
              w.writeArrayHeader(1);

              w.writeMapHeader(1);
              w.writeString(wrapString("longProp"));
              w.writeInteger(753L);

              w.writeString(wrapString("notEmptyDefaultArray"));
              w.writeArrayHeader(0);
            });

    // when
    pojo.wrap(buffer);

    // then
    final Iterator<MinimalPOJO> iterator1 = pojo.simpleArray().iterator();
    assertThat(iterator1.hasNext()).isTrue();
    assertThat(iterator1.next().getLongProp()).isEqualTo(123L);
    assertThat(iterator1.hasNext()).isTrue();
    assertThat(iterator1.next().getLongProp()).isEqualTo(456L);
    assertThat(iterator1.hasNext()).isTrue();
    assertThat(iterator1.next().getLongProp()).isEqualTo(789L);
    assertThat(iterator1.hasNext()).isTrue();
    assertThat(iterator1.next().getLongProp()).isEqualTo(555L);
    assertThat(iterator1.hasNext()).isTrue();
    assertThat(iterator1.next().getLongProp()).isEqualTo(777L);
    assertThat(iterator1.hasNext()).isFalse();
  }

}