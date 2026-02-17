class DummyClass_31007 {
  @Test
  public void shouldNotInvalidElementOnRemove() {
    // given
    addIntValues(array, 1, 2, 3);

    // when
    final Iterator<IntegerValue> iterator = array.iterator();
    final IntegerValue element = iterator.next();
    iterator.remove();

    // then
    assertThat(element.getValue()).isEqualTo(1);
    encodeAndDecode(array);
    assertIntValues(array, 2, 3);
  }

}