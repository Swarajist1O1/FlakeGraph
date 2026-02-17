class DummyClass_31000 {
  @Test
  public void shouldAddValuesAtEndAfterRead() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.next();
    iterator.next();
    addIntValues(array, 4, 5, 6);

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 2, 3, 4, 5, 6);
  }

}