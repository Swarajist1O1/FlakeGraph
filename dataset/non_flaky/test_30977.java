class DummyClass_30977 {
  @Test
  public void shouldFailReadingInvalidUndeclaredProperty() {
    // given
    final MinimalPOJO pojo = new MinimalPOJO();

    final MutableDirectBuffer msgPack =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(2);
              w.writeString(wrapString("longProp"));
              w.writeInteger(123L);
              w.writeInteger(789L);
              w.writeInteger(123L);
            });

    // then
    exception.expect(RuntimeException.class);
    exception.expectMessage("Could not deserialize object");

    // when
    pojo.wrap(msgPack);
  }

}