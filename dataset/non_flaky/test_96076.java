class DummyClass_96076 {
  @Test
  public void testOpenIEPipeline() {
    testAnnotatorSequence(Arrays.asList("tokenize","ssplit","pos","lemma","depparse","natlog","openie"));
  }

}