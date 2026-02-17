class DummyClass_176921 {
  @Test
  public void testMultiRecommendRescorer() {
    RescorerProvider multi = new MultiRescorerProvider(
        new SimpleModRescorerProvider(2), new SimpleModRescorerProvider(3));
    
    Rescorer provider = multi.getRecommendRescorer(Collections.singletonList("ABCDE"), null);
    assertNull(provider);

    Rescorer provider2 = multi.getRecommendRescorer(Collections.singletonList("AB"), null);
    assertNotNull(provider2);
    assertFalse(provider2 instanceof MultiRescorer);
    assertTrue(provider2.isFiltered("ABC"));
    assertFalse(provider2.isFiltered("AB"));

    Rescorer provider3 = multi.getRecommendRescorer(Collections.singletonList("ABCDEF"), null);
    assertNotNull(provider3);
    assertTrue(provider3 instanceof MultiRescorer);
    assertTrue(provider3.isFiltered("ABC"));
    assertTrue(provider3.isFiltered("AB"));
    assertFalse(provider3.isFiltered("ABCDEFABCDEF"));
  }

}