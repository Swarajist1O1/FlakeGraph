class DummyClass_96044 {
  @Test
  public void testTokenSequenceMatcherMultiNodePattern2() throws IOException {
    CoreMap doc = createDocument("Replace the lamp with model wss.32dc55c3e945384dbc5e533ab711fd24");

    // Greedy
    TokenSequencePattern p = TokenSequencePattern.compile("/model/ ((?m){1,4}/\\w+\\.\\w+/)");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("model wss.32dc55c3e945384dbc5e533ab711fd24", m.group());
    assertEquals("wss.32dc55c3e945384dbc5e533ab711fd24", m.group(1));
    match = m.find();
    assertFalse(match);

    // Reluctant
    p = TokenSequencePattern.compile("/model/ ((?m){1,4}?/\\w+\\.\\w+/)");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("model wss.32", m.group());
    assertEquals("wss.32", m.group(1));
    match = m.find();
    assertFalse(match);
  }

}