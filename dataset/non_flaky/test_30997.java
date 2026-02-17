class DummyClass_30997 {
  @Test
  public void shouldAppendValues() {
    // when
    addIntValues(array, 1, 2, 3);

    // then
    encodeAndDecode(array);
    assertIntValues(array, 1, 2, 3);
  }

}