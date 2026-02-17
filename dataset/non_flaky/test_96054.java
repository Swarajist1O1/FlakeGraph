class DummyClass_96054 {
  // @Test
  public void testOnlyGloss() {
    List<List<String>> entries = Collections.singletonList(
            Arrays.asList("124", "docid1", "1", "This is a test document."));

    TSVSentenceIterator it = new TSVSentenceIterator(entries.iterator(),
            Arrays.asList(SentenceField.ID, SentenceField.DOC_ID, SentenceField.SENTENCE_INDEX, SentenceField.GLOSS));
    Sentence sentence = it.next();
    Assert.assertEquals(1, sentence.sentenceIndex());
    Assert.assertEquals("This is a test document."  , sentence.text());
    Assert.assertEquals("docid1", sentence.asCoreMap().get(CoreAnnotations.DocIDAnnotation.class));
    Assert.assertEquals("124", sentence.asCoreMap().get(CoreAnnotations.SentenceIDAnnotation.class));
  }

}