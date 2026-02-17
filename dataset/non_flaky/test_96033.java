class DummyClass_96033 {
  @Test
  public void testTokenSequenceMatcher10() throws IOException {
    CoreMap doc = createDocument("the number is five or 5 or 5.0 or but not 5x or -5 or 5L.");

    // Test simplified pattern with number
    TokenSequencePattern p = TokenSequencePattern.compile( "(five|5|5x|5.0|-5|5L)");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("five", m.group(1));

    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("5", m.group(1));

    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("5.0", m.group(1));

    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("5x", m.group(1));

    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("-5", m.group(1));

    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("5L", m.group(1));

    match = m.find();
    assertFalse(match);
  }

}