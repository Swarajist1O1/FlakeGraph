class DummyClass_30990 {
  @Test
  public void shouldNotDeserializePOJOFromNonMap() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeString(wrapString("stringProp"));
              w.writeFloat(123123.123123d);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage(
        "Could not deserialize object [POJO]. Deserialization stuck at offset 1");

    // when
    pojo.wrap(buffer);
  }

}