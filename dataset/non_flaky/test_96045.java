class DummyClass_96045 {
  @Test
  public void testTokenSequenceMatcherBackRef() throws IOException {
    CoreMap doc = createDocument("A A A A A A A B A A B A C A E A A A A A A A A A A A B A A A");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "(/A/+) B \\1");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A B A A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A B A A A", m.group());
    match = m.find();
    assertFalse(match);

  }

}