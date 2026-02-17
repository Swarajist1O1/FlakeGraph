class DummyClass_31001 {
  @Test
  public void shouldUpdateValues() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    iterator.next().setValue(4);
    iterator.next().setValue(5);
    iterator.next().setValue(6);

    // then
    encodeAndDecode(array);
    assertIntValues(array, 4, 5, 6);
  }

}