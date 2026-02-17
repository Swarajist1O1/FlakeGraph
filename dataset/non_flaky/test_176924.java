class DummyClass_176924 {
  @Test
  public void testMultiMostActiveUsersRescorer() {
    RescorerProvider multi = new MultiRescorerProvider(
        new SimpleModRescorerProvider(2), new SimpleModRescorerProvider(3));
    Rescorer provider = multi.getMostActiveUsersRescorer(null);
    assertNotNull(provider);
    assertTrue(provider instanceof MultiRescorer);
    assertTrue(provider.isFiltered("ABC"));
    assertTrue(provider.isFiltered("AB"));
    assertFalse(provider.isFiltered("ABCDEF"));
  }

}