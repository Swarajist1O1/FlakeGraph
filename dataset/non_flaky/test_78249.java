class DummyClass_78249 {
  @Test
  public void testCombiningValue() throws Exception {

    GroupingState<Integer, Integer> value = underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR);

    // State instances are cached, but depend on the namespace.
    assertEquals(value, underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR));
    assertFalse(value.equals(underTest.state(NAMESPACE_2, SUM_INTEGER_ADDR)));

    assertThat(value.read(), equalTo(0));
    value.add(2);
    assertThat(value.read(), equalTo(2));

    value.add(3);
    assertThat(value.read(), equalTo(5));

    value.clear();
    assertThat(value.read(), equalTo(0));
    assertThat(underTest.state(NAMESPACE_1, SUM_INTEGER_ADDR), equalTo(value));
  }

}