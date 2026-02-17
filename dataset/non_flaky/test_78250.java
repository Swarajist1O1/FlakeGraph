class DummyClass_78250 {
  @Test
  public void testCombiningIsEmpty() throws Exception {
    GroupingState<Integer, Integer> value = underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR);

    assertThat(value.isEmpty().read(), Matchers.is(true));
    ReadableState<Boolean> readFuture = value.isEmpty();
    value.add(5);
    assertThat(readFuture.read(), Matchers.is(false));

    value.clear();
    assertThat(readFuture.read(), Matchers.is(true));
  }

}