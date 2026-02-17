class DummyClass_30996 {
  @Test
  public void shouldDeserializeWithReusedPOJO() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buf1 =
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
              w.writeInteger(123);

              w.writeString(wrapString("objectProp"));
              w.writeRaw(MSGPACK_BUF1);
            });
    pojo.wrap(buf1);

    final DirectBuffer buf2 =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(7);

              w.writeString(wrapString("enumProp"));
              w.writeString(wrapString(POJOEnum.FOO.toString()));

              w.writeString(wrapString("binaryProp"));
              w.writeBinary(BUF2);

              w.writeString(wrapString("stringProp"));
              w.writeString(BUF1);

              w.writeString(wrapString("packedProp"));
              w.writeRaw(MSGPACK_BUF2);

              w.writeString(wrapString("longProp"));
              w.writeInteger(7777L);

              w.writeString(wrapString("intProp"));
              w.writeInteger(456);

              w.writeString(wrapString("objectProp"));
              w.writeRaw(MSGPACK_BUF3);
            });

    // when
    pojo.reset();
    pojo.wrap(buf2);

    // then
    assertThat(pojo.getEnum()).isEqualByComparingTo(POJOEnum.FOO);
    assertThat(pojo.getLong()).isEqualTo(7777L);
    assertThat(pojo.getInt()).isEqualTo(456);
    assertThatBuffer(pojo.getPacked()).hasBytes(MSGPACK_BUF2);
    assertThatBuffer(pojo.getBinary()).hasBytes(BUF2);
    assertThatBuffer(pojo.getString()).hasBytes(BUF1);
    assertThat(pojo.nestedObject().getLong()).isEqualTo(24L);
  }

}