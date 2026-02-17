class DummyClass_31006 {
  @Test
  public void shouldRemoveAllValues() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.remove();
    iterator.next();
    iterator.remove();
    iterator.next();
    iterator.remove();

    // then
    encodeAndDecode(array);
    assertIntValues(array);
  }

}