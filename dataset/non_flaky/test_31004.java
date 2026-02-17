class DummyClass_31004 {
  @Test
  public void shouldRemoveValueInBetween() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.next();
    iterator.remove();

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 3);
  }

}