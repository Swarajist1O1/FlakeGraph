class DummyClass_30970 {
  @Test
  public void shouldFailOnRemovingWhenEntryHasBeenAddedBefore() {
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
    pojo.simpleArray().add().setLongProp(999L);

    // then
    exception.expect(IllegalStateException.class);

    // when
    iterator.remove();
  }

}