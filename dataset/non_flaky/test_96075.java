class DummyClass_96075 {
   @Test
   public void testTrueCasePipeline() {
     testAnnotatorSequence(Arrays.asList("tokenize","ssplit","pos","lemma","truecase"));
   }

}