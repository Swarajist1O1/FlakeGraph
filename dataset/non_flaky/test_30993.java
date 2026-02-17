class DummyClass_30993 {
  @Test
  public void shouldFailDeserializationWithUndersizedIntegerValue() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(6);

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
              w.writeInteger(Integer.MIN_VALUE - 1L);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage("Could not deserialize object");

    // when
    pojo.wrap(buffer);
  }

}