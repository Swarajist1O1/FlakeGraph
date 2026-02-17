class DummyClass_30987 {
  @Test
  public void shouldDeserializePOJO() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(7);

              w.writeString(wrapString("enumProp"));
              w.writeString(wrapString(POJOEnum.BAR.toString()));

              w.writeString(wrapString("binaryProp"));
              w.writeBinary(BUF1);

              w.writeString(wrapString("stringProp"));
              w.writeString(BUF2);

              w.writeString(wrapString("packedProp"));
              w.writeRaw(MSGPACK_BUF1);

              w.writeString(wrapString("longProp"));
              w.writeInteger(88888L);

              w.writeString(wrapString("intProp"));
              w.writeInteger(123L);

              w.writeString(wrapString("objectProp"));
              w.writeRaw(MSGPACK_BUF1);
            });

    // when
    pojo.wrap(buffer);

    // then
    assertThat(pojo.getEnum()).isEqualByComparingTo(POJOEnum.BAR);
    assertThat(pojo.getLong()).isEqualTo(88888L);
    assertThat(pojo.getInt()).isEqualTo(123);
    assertThatBuffer(pojo.getPacked()).hasBytes(MSGPACK_BUF1);
    assertThatBuffer(pojo.getBinary()).hasBytes(BUF1);
    assertThatBuffer(pojo.getString()).hasBytes(BUF2);
    assertThat(pojo.nestedObject().getLong()).isEqualTo(123123L);
  }

}