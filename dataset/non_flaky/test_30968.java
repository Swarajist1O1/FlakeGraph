class DummyClass_30968 {
  @Test
  public void shouldFailOnInitialRemove() {
    // given
    final POJOArray pojo = new POJOArray();

    final DirectBuffer buffer =
        encodeMsgPack(
            (w) -> {
              w.writeMapHeader(1);
              encodeSimpleArrayProp(w);
            });

    pojo.wrap(buffer);
    final Iterator<MinimalPOJO> iterator = pojo.simpleArray().iterator();

    // then
    exception.expect(IllegalStateException.class);

    // when
    iterator.remove();
  }

}