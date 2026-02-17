class DummyClass_30916 {
  @Test
  public void shouldSkipElementsBasedOnPredicate() {
    // given
    final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    final IntegerStream wrapper = new IntegerStream(stream);

    // when
    final List<Integer> result = wrapper.skipUntil(i -> i == 3).asList();

    // then
    assertThat(result).containsExactly(3, 4, 5);
  }

}