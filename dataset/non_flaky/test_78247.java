class DummyClass_78247 {
  @Test
  public void testMergeSetIntoNewNamespace() throws Exception {

    SetState<String> set1 = underTest.state(NAMESPACE_1, STRING_SET_ADDR);
    SetState<String> set2 = underTest.state(NAMESPACE_2, STRING_SET_ADDR);
    SetState<String> set3 = underTest.state(NAMESPACE_3, STRING_SET_ADDR);

    set1.add("Hello");
    set2.add("Hello");
    set2.add("World");
    set1.add("!");

    StateMerging.mergeSets(Arrays.asList(set1, set2, set3), set3);

    // Reading the merged set gets both the contents
    assertThat(set3.read(), containsInAnyOrder("Hello", "World", "!"));
    assertThat(set1.read(), Matchers.emptyIterable());
    assertThat(set2.read(), Matchers.emptyIterable());
  }

}