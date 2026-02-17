class DummyClass_78258 {
  @Test
  public void testMapReadable() throws Exception {
    MapState<String, Integer> value = underTest.state(NAMESPACE_1, STRING_MAP_ADDR);

    // test iterable, should just return a iterable view of the values contained in this map.
    // The iterable is backed by the map, so changes to the map are reflected in the iterable.
    ReadableState<Iterable<String>> keys = value.keys();
    ReadableState<Iterable<Integer>> values = value.values();
    ReadableState<Iterable<Map.Entry<String, Integer>>> entries = value.entries();
    value.put("A", 1);
    assertFalse(Iterables.isEmpty(keys.read()));
    assertFalse(Iterables.isEmpty(values.read()));
    assertFalse(Iterables.isEmpty(entries.read()));

    // test get
    ReadableState<Integer> get = value.get("B");
    value.put("B", 2);
    assertNull(get.read());

    // test addIfAbsent
    value.putIfAbsent("C", 3);
    assertThat(value.get("C").read(), equalTo(3));
  }

}