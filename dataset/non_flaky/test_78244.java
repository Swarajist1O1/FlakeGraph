class DummyClass_78244 {
  @Test
  public void testSet() throws Exception {

    SetState<String> value = underTest.state(NAMESPACE_1, STRING_SET_ADDR);

    // State instances are cached, but depend on the namespace.
    assertThat(value, equalTo(underTest.state(NAMESPACE_1, STRING_SET_ADDR)));
    assertThat(value, not(equalTo(underTest.state(NAMESPACE_2, STRING_SET_ADDR))));

    // empty
    assertThat(value.read(), Matchers.emptyIterable());
    assertFalse(value.contains("A").read());

    // add
    value.add("A");
    value.add("B");
    value.add("A");
    assertFalse(value.addIfAbsent("B").read());
    assertThat(value.read(), containsInAnyOrder("A", "B"));

    // remove
    value.remove("A");
    assertThat(value.read(), containsInAnyOrder("B"));
    value.remove("C");
    assertThat(value.read(), containsInAnyOrder("B"));

    // contains
    assertFalse(value.contains("A").read());
    assertTrue(value.contains("B").read());
    value.add("C");
    value.add("D");

    // readLater
    assertThat(value.readLater().read(), containsInAnyOrder("B", "C", "D"));
    SetState<String> later = value.readLater();
    assertThat(later.read(), hasItems("C", "D"));
    assertFalse(later.contains("A").read());

    // clear
    value.clear();
    assertThat(value.read(), Matchers.emptyIterable());
    assertThat(underTest.state(NAMESPACE_1, STRING_SET_ADDR), equalTo(value));
  }

}