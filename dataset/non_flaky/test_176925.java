class DummyClass_176925 {
  @Test
  public void testMultiMostSimilarItemsRescorer() {
    RescorerProvider multi = new MultiRescorerProvider(
        new SimpleModRescorerProvider(2), new SimpleModRescorerProvider(3));
    Rescorer provider = multi.getMostSimilarItemsRescorer(null);
    assertNotNull(provider);
    assertTrue(provider instanceof MultiRescorer);
    assertTrue(provider.isFiltered("ABC"));
    assertTrue(provider.isFiltered("ABCDE"));
    assertFalse(provider.isFiltered("ABCDEFABCDEF"));
  }

}