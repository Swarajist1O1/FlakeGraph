class DummyClass_96048 {
  @Test
  public void testStringMatchCaseInsensitive() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test simple sequence
    Env env = TokenSequencePattern.getNewEnv();
    env.setDefaultStringMatchFlags(NodePattern.CASE_INSENSITIVE);
    TokenSequencePattern p = TokenSequencePattern.compile(env, "archbishop of canterbury");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    assertTrue(m.find());
    assertEquals("Archbishop of Canterbury", m.group());
    assertFalse(m.find());

    p = TokenSequencePattern.compile(env, "ARCHBISHOP OF CANTERBURY");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    assertTrue(m.find());
    assertEquals("Archbishop of Canterbury", m.group());
    assertFalse(m.find());
  }

}