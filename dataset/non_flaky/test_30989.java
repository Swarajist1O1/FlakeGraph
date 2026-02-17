class DummyClass_30989 {
  @Test
  public void shouldNotDeserializePOJOWithWrongKeyType() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);

              w.writeInteger(123123L);
              w.writeFloat(123123.123123d);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage(
        "Could not deserialize object [POJO]. Deserialization stuck at offset 2");

    // when
    pojo.wrap(buffer);
  }

}