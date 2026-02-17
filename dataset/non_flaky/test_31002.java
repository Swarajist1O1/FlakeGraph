class DummyClass_31002 {
  @Test
  public void shouldSerializeValuesAfterPartialRead() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.next();

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 2, 3);
  }

}