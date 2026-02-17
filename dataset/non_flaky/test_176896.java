class DummyClass_176896 {
  @Test
  public void testBySecond() {
    List<Pair<Integer,String>> pairs = Arrays.asList(
        new Pair<>(3, "foo"),
        new Pair<>(4, "bing"),
        new Pair<>(1, "baz"),
        new Pair<>(2, "whizz")
    );
    Collections.sort(pairs, PairComparators.<String>bySecond());
    assertEquals(1, pairs.get(0).getFirst().intValue());
    assertEquals(4, pairs.get(1).getFirst().intValue());
    assertEquals("baz", pairs.get(0).getSecond());
    assertEquals("bing", pairs.get(1).getSecond());
  }

}