class DummyClass_31005 {
  @Test
  public void shouldRemoveValueAtEnd() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.next();
    iterator.next();
    iterator.remove();

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 2);
  }

}