class DummyClass_31009 {
  @Test
  public void shouldUpdateWithBiggerValue() {
    // given
    final ArrayValue<StringValue> array = new ArrayValue<>(new StringValue());
    addStringValues(array, "foo", "bar", "baz");

    // when
    final Iterator<StringValue> iterator = array.iterator();
    StringValue element = iterator.next();
    element.wrap(BufferUtil.wrapString("hello"));
    element = iterator.next();
    element.wrap(BufferUtil.wrapString("world"));
    element = iterator.next();
    element.wrap(BufferUtil.wrapString("friend"));

    // then
    encodeAndDecode(array);
    assertStringValues(array, "hello", "world", "friend");
  }

}