class DummyClass_78251 {
  @Test
  public void testMergeCombiningValueIntoSource() throws Exception {
    CombiningState<Integer, int[], Integer> value1 = underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR);
    CombiningState<Integer, int[], Integer> value2 = underTest.state(NAMESPACE_2, SUM_INTEGER_ADDR);

    value1.add(5);
    value2.add(10);
    value1.add(6);

    assertThat(value1.read(), equalTo(11));
    assertThat(value2.read(), equalTo(10));

    // Merging clears the old values and updates the result value.
    StateMerging.mergeCombiningValues(Arrays.asList(value1, value2), value1);

    assertThat(value1.read(), equalTo(21));
    assertThat(value2.read(), equalTo(0));
  }

}