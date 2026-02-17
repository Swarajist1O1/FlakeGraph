class DummyClass_78243 {
  @Test
  public void testMergeBagIntoNewNamespace() throws Exception {
    BagState<String> bag1 = underTest.state(NAMESPACE_1, STRING_BAG_ADDR);
    BagState<String> bag2 = underTest.state(NAMESPACE_2, STRING_BAG_ADDR);
    BagState<String> bag3 = underTest.state(NAMESPACE_3, STRING_BAG_ADDR);

    bag1.add("Hello");
    bag2.add("World");
    bag1.add("!");

    StateMerging.mergeBags(Arrays.asList(bag1, bag2, bag3), bag3);

    // Reading the merged bag gets both the contents
    assertThat(bag3.read(), containsInAnyOrder("Hello", "World", "!"));
    assertThat(bag1.read(), Matchers.emptyIterable());
    assertThat(bag2.read(), Matchers.emptyIterable());
  }

}