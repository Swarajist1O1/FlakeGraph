class DummyClass_30967 {
  @Test
  public void shouldDeserializePOJOWithDefaultValues() {
    // given
    final POJOArray pojo = new POJOArray();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              encodeSimpleArrayProp(w);
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