class DummyClass_31010 {
  @Test
  public void shouldIncreaseInternalBufferWhenAddingToEnd() {
    // given
    final int valueCount = 10_000;

    final Integer[] values =
        IntStream.iterate(0, (i) -> ++i)
            .limit(valueCount)
            .boxed()
            .collect(Collectors.toList())
            .toArray(new Integer[valueCount]);

    // when
    addIntValues(array, values);

    // then
    encodeAndDecode(array);
    assertIntValues(array, values);
  }

}