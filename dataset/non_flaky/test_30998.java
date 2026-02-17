class DummyClass_30998 {
  @Test
  public void shouldAddValueAtBeginning() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    // reset iterator to append at beginning
    array.iterator();
    addIntValues(array, 4, 5, 6);

    // then
    encodeAndDecode(array);
    assertIntValues(array, 4, 5, 6, 1, 2, 3);
  }

}