class DummyClass_31008 {
  @Test
  public void shouldUpdateWithSmallerValue() {
    // given
    final ArrayValue<StringValue> array = new ArrayValue<>(new StringValue());
    addStringValues(array, "foo", "bar", "baz");

    // when
    final Iterator<StringValue> iterator = array.iterator();
    StringValue element = iterator.next();
    element.wrap(BufferUtil.wrapString("a"));
    element = iterator.next();
    element.wrap(BufferUtil.wrapString("b"));
    element = iterator.next();
    element.wrap(BufferUtil.wrapString("c"));

    // then
    encodeAndDecode(array);
    assertStringValues(array, "a", "b", "c");
  }

}