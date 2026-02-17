class DummyClass_30992 {
  @Test
  public void shouldFailDeserializationWithOversizedIntegerValue() {
    // given
    final POJO pojo = new POJO();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);

              w.writeString(wrapString("intProp"));
              w.writeInteger(Integer.MAX_VALUE + 1L);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage("Could not deserialize object");

    // when
    pojo.wrap(buffer);
  }

}