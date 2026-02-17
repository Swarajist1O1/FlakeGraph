class DummyClass_96082 {
  @Test
  public void testCollapsedGraphs() throws IOException {
    Annotation annotation = new Annotation("I bought a bone for my dog.");
    fullPipeline.annotate(annotation);
    runTest(annotation);
  }

}