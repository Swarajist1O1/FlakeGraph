class DummyClass_31011 {
  @Test
  public void shouldIncreaseInternalBufferWhenAddingToBeginning() {
    // given
    final int valueCount = 10_000;
    final List<Integer> generatedList =
        IntStream.iterate(0, (i) -> ++i).limit(valueCount).boxed().collect(Collectors.toList());
    final List<Integer> reverseList = new ArrayList<>(generatedList);
    Collections.reverse(generatedList);

    final Integer[] values = generatedList.toArray(new Integer[valueCount]);

    // when
    for (final Integer value : values) {
      // reset cursor to first position
      array.iterator();
      array.add().setValue(value);
    }

    // then
    encodeAndDecode(array);

    final Integer[] resultValues = reverseList.toArray(new Integer[valueCount]);
    assertIntValues(array, resultValues);
  }

}