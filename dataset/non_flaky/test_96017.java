class DummyClass_96017 {
  @Test
  public void testTokenSequenceMatcherBeginEnd() throws IOException {
    CoreMap doc = createDocument(testText);

    // Test simple sequence with begin sequence matching
    TokenSequencePattern p = TokenSequencePattern.compile("^ [] []");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));

    boolean match = m.find();
    assertTrue(match);
    assertEquals("the number", m.group());

    match = m.find();
    assertFalse(match);

    // Test simple sequence with end sequence matching
    p = TokenSequencePattern.compile("[] [] $");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));

    match = m.find();
    assertTrue(match);
    assertEquals("fifty.", m.group());

    match = m.find();
    assertFalse(match);

    // Test simple sequence with begin and end sequence matching
    p = TokenSequencePattern.compile("^ [] [] $");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));

    match = m.find();
    assertFalse(match);

    // Test simple sequence with ^$ in a string regular expression
    p = TokenSequencePattern.compile("/^number$/");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));

    match = m.find();
    assertTrue(match);
    assertEquals("number", m.group());

    match = m.find();
    assertFalse(match);
  }

}