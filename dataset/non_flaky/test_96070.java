class DummyClass_96070 {
  @Test
  public void testEmptyAnnotation() {
    try {
      annotator.annotate(new Annotation(""));
    } catch(RuntimeException e) {
      return;
    }
    Assert.fail("Never expected to get this far... the annotator should have thrown an exception by now");
  }

}