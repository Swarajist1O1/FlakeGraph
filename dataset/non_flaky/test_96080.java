class DummyClass_96080 {
  @Test
  public void testCoreQuote() {
    // make the core document
    CoreDocument testDoc = new CoreDocument(testDocText);
    // annotate
    pipeline.annotate(testDoc);
    // test canonical entity mention is correct
    // "Joe Smith" should be first entity mention
    CoreMap canonicalEntityMention =
        testDoc.annotation().get(CoreAnnotations.MentionsAnnotation.class).get(1);
    // test canonical mention is correct
    assertEquals("Joe Smith", canonicalEntityMention.get(CoreAnnotations.TextAnnotation.class));
    assertEquals(14,
        canonicalEntityMention.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class).intValue());
    assertEquals(23,
        canonicalEntityMention.get(CoreAnnotations.CharacterOffsetEndAnnotation.class).intValue());
    // test the CoreQuote has the correct entity mention for the canonical speaker
    assertEquals(canonicalEntityMention, testDoc.quotes().get(0).canonicalSpeakerEntityMention().get().coreMap());
  }

}