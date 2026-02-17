class DummyClass_104668 {
  @Test
  public void testGeneratedQueries()
      throws Exception {
    for (int i = 0; i < NUM_QUERIES_TO_GENERATE; i += 2) {
      testStarQuery(_starTree1QueryGenerator.nextQuery());
      testStarQuery(_starTree2QueryGenerator.nextQuery());
    }
  }

}