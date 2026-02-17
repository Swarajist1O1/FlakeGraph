class DummyClass_78252 {
  @Test
  public void testMergeCombiningValueIntoNewNamespace() throws Exception {
    CombiningState<Integer, int[], Integer> value1 = underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR);
    CombiningState<Integer, int[], Integer> value2 = underTest.state(NAMESPACE_2, SUM_INTEGER_ADDR);
    CombiningState<Integer, int[], Integer> value3 = underTest.state(NAMESPACE_3, SUM_INTEGER_ADDR);

    value1.add(5);
    value2.add(10);
    value1.add(6);

    StateMerging.mergeCombiningValues(Arrays.asList(value1, value2), value3);

    // Merging clears the old values and updates the result value.
    assertThat(value1.read(), equalTo(0));
    assertThat(value2.read(), equalTo(0));
    assertThat(value3.read(), equalTo(21));
  }

}