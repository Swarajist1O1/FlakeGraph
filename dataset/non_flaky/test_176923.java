class DummyClass_176923 {
  @Test
  public void testMultiMostPopularItemsRescorer() {
    RescorerProvider multi = new MultiRescorerProvider(
        new SimpleModRescorerProvider(2), new SimpleModRescorerProvider(3));
    Rescorer provider = multi.getMostPopularItemsRescorer(null);
    assertNotNull(provider);
    assertTrue(provider instanceof MultiRescorer);
    assertTrue(provider.isFiltered("ABC"));
    assertTrue(provider.isFiltered("AB"));
    assertFalse(provider.isFiltered("ABCDEF"));
  }

}