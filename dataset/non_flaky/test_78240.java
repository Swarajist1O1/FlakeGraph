class DummyClass_78240 {
  @Test
  public void testBag() throws Exception {
    BagState<String> value = underTest.state(NAMESPACE_1, STRING_BAG_ADDR);

    // State instances are cached, but depend on the namespace.
    assertThat(value, equalTo(underTest.state(NAMESPACE_1, STRING_BAG_ADDR)));
    assertThat(value, not(equalTo(underTest.state(NAMESPACE_2, STRING_BAG_ADDR))));

    assertThat(value.read(), Matchers.emptyIterable());
    value.add("hello");
    assertThat(value.read(), containsInAnyOrder("hello"));

    value.add("world");
    assertThat(value.read(), containsInAnyOrder("hello", "world"));

    value.clear();
    assertThat(value.read(), Matchers.emptyIterable());
    assertThat(underTest.state(NAMESPACE_1, STRING_BAG_ADDR), equalTo(value));
  }

}