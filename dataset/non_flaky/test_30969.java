class DummyClass_30969 {
  @Test
  public void shouldFailOnRemovingEntryTwice() {
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
    iterator.next();
    iterator.remove();

    // then
    exception.expect(IllegalStateException.class);

    // when
    iterator.remove();
  }

}