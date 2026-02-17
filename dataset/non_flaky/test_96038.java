class DummyClass_96038 {
  @Test
  public void testTokenSequenceMatcherNested() throws IOException {
    CoreMap doc = createDocument("A A A B B B B B B C C");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "( /B/+ )+");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("B B B B B B", m.group());
    assertEquals("B B B B B B", m.group(1));
    match = m.find();
    assertFalse(match);
  }

}