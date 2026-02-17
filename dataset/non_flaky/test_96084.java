class DummyClass_96084 {
  @Test
  public void testCopyWordGraphs() throws IOException {
    Annotation annotation = new Annotation("I went over the river and through the woods");
    fullPipeline.annotate(annotation);
    runTest(annotation);
  }

}