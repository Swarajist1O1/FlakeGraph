class DummyClass_78242 {
  @Test
  public void testMergeBagIntoSource() throws Exception {
    BagState<String> bag1 = underTest.state(NAMESPACE_1, STRING_BAG_ADDR);
    BagState<String> bag2 = underTest.state(NAMESPACE_2, STRING_BAG_ADDR);

    bag1.add("Hello");
    bag2.add("World");
    bag1.add("!");

    StateMerging.mergeBags(Arrays.asList(bag1, bag2), bag1);

    // Reading the merged bag gets both the contents
    assertThat(bag1.read(), containsInAnyOrder("Hello", "World", "!"));
    assertThat(bag2.read(), Matchers.emptyIterable());
  }

}