class DummyClass_176922 {
  @Test
  public void testMultiRecommendToAnonymousRescorer() {
    RescorerProvider multi = new MultiRescorerProvider(
        new SimpleModRescorerProvider(2), new SimpleModRescorerProvider(3));
    
    Rescorer provider = multi.getRecommendToAnonymousRescorer(
        Collections.singletonList("ABCDE"), null);
    assertNull(provider);

    Rescorer provider2 =
        multi.getRecommendToAnonymousRescorer(Collections.singletonList("AB"), null);
    assertNotNull(provider2);
    assertFalse(provider2 instanceof MultiRescorer);
    assertTrue(provider2.isFiltered("ABC"));
    assertFalse(provider2.isFiltered("AB"));

    Rescorer provider3 =
        multi.getRecommendToAnonymousRescorer(Collections.singletonList("ABCDEF"), null);
    assertNotNull(provider3);
    assertTrue(provider3 instanceof MultiRescorer);
    assertTrue(provider3.isFiltered("ABC"));
    assertTrue(provider3.isFiltered("AB"));
    assertFalse(provider3.isFiltered("ABCDEF"));
  }

}