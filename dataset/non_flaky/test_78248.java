class DummyClass_78248 {
  @Test
  public void testMap() throws Exception {

    MapState<String, Integer> value = underTest.state(NAMESPACE_1, STRING_MAP_ADDR);

    // State instances are cached, but depend on the namespace.
    assertThat(value, equalTo(underTest.state(NAMESPACE_1, STRING_MAP_ADDR)));
    assertThat(value, not(equalTo(underTest.state(NAMESPACE_2, STRING_MAP_ADDR))));

    // put
    assertThat(value.entries().read(), Matchers.emptyIterable());
    value.put("A", 1);
    value.put("B", 2);
    value.put("A", 11);
    assertThat(value.putIfAbsent("B", 22).read(), equalTo(2));
    assertThat(
        value.entries().read(), containsInAnyOrder(MapEntry.of("A", 11), MapEntry.of("B", 2)));

    // remove
    value.remove("A");
    assertThat(value.entries().read(), containsInAnyOrder(MapEntry.of("B", 2)));
    value.remove("C");
    assertThat(value.entries().read(), containsInAnyOrder(MapEntry.of("B", 2)));

    // get
    assertNull(value.get("A").read());
    assertThat(value.get("B").read(), equalTo(2));
    value.put("C", 3);
    value.put("D", 4);
    assertThat(value.get("C").read(), equalTo(3));

    // iterate
    value.put("E", 5);
    value.remove("C");
    assertThat(value.keys().read(), containsInAnyOrder("B", "D", "E"));
    assertThat(value.values().read(), containsInAnyOrder(2, 4, 5));
    assertThat(
        value.entries().read(),
        containsInAnyOrder(MapEntry.of("B", 2), MapEntry.of("D", 4), MapEntry.of("E", 5)));

    // readLater
    assertThat(value.get("B").readLater().read(), equalTo(2));
    assertNull(value.get("A").readLater().read());
    assertThat(
        value.entries().readLater().read(),
        containsInAnyOrder(MapEntry.of("B", 2), MapEntry.of("D", 4), MapEntry.of("E", 5)));

    // clear
    value.clear();
    assertThat(value.entries().read(), Matchers.emptyIterable());
    assertThat(underTest.state(NAMESPACE_1, STRING_MAP_ADDR), equalTo(value));
  }

}