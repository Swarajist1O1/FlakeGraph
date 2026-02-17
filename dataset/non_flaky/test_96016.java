class DummyClass_96016 {
  @Test
  public void testTokenSequenceMatcherValue() throws IOException {
    CoreMap doc = createDocument(testText);

    // Test simple sequence with value
    TokenSequencePattern p = TokenSequencePattern.compile(getOrPatternExpr(
            new Pair<String,Object>("one", 1), new Pair<String,Object>("two", null), new Pair<String,Object>("fifty", 50)));
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));

    boolean match = m.find();
    assertTrue(match);
    assertEquals("one", m.group());
    assertEquals(1, m.groupValue());

    match = m.find();
    assertTrue(match);
    assertEquals("two", m.group());
    assertNull(m.groupValue());

    match = m.find();
    assertTrue(match);
    assertEquals("fifty", m.group());
    assertEquals(50, m.groupValue());

    match = m.find();
    assertFalse(match);
  }

}