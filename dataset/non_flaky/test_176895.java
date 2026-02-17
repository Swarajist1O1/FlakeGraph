class DummyClass_176895 {
  @Test
  public void testByFirst() {
    List<Pair<Integer,String>> pairs = Arrays.asList(
        new Pair<>(3, "foo"),
        new Pair<>(4, "bing"),
        new Pair<>(1, "baz"),
        new Pair<>(2, "whizz")
    );
    Collections.sort(pairs, PairComparators.<Integer>byFirst());
    assertEquals(1, pairs.get(0).getFirst().intValue());
    assertEquals(2, pairs.get(1).getFirst().intValue());
    assertEquals("baz", pairs.get(0).getSecond());
    assertEquals("whizz", pairs.get(1).getSecond());
  }

}