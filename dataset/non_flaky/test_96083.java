class DummyClass_96083 {
  @Test
  public void testTwoSentences() throws IOException {
    Annotation annotation = new Annotation("I bought a bone for my dog.  He chews it every day.");
    fullPipeline.annotate(annotation);
    runTest(annotation);
  }

}