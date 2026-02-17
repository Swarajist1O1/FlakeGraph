class DummyClass_31003 {
  @Test
  public void shouldRemoveValueAtBeginning() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next();
    iterator.remove();

    // then
    encodeAndDecode(array);
    assertIntValues(array, 2, 3);
  }

}