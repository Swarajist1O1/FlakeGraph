class DummyClass_30988 {
  @Test
  public void shouldNotDeserializePOJOWithWrongValueType() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);

              w.writeString(wrapString("stringProp"));
              w.writeFloat(123123.123123d);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage(
        "Could not deserialize object [POJO]. Deserialization stuck at offset 13");

    // when
    pojo.wrap(buffer);
  }

}