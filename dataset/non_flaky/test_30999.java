class DummyClass_30999 {
  @Test
  public void shouldAddValueInBetween() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    addIntValues(array, 4, 5, 6);

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 4, 5, 6, 2, 3);
  }

}