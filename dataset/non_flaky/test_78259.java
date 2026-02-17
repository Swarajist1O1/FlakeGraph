class DummyClass_78259 {
  @Test
  public void testBagWithBadCoderEquality() throws Exception {
    // Ensure two instances of the bad coder are distinct; models user who fails to
    // override equals() or inherit from CustomCoder for StructuredCoder
    assertThat(
        new StringCoderWithIdentityEquality(), not(equalTo(new StringCoderWithIdentityEquality())));

    BagState<String> state1 = underTest.state(NAMESPACE_1, STRING_BAG_ADDR1);
    state1.add("hello");

    BagState<String> state2 = underTest.state(NAMESPACE_1, STRING_BAG_ADDR2);
    assertThat(state2.read(), containsInAnyOrder("hello"));
  }

}