class DummyClass_96081 {
  @Test
  public void testSimple() throws IOException {
    Annotation annotation = new Annotation("This is a test");
    fullPipeline.annotate(annotation);
    runTest(annotation);
  }

}