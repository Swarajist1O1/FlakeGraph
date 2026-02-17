class DummyClass_96027 {
  @Test
  public void testTokenSequenceMatcher4() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile(
                      new SequencePattern.RepeatPatternExpr(
                                    getSequencePatternExpr("[A-Za-z]+"), 1, -1));

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus was the first Bishop of London", m.group());

    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("the third Archbishop of Canterbury", m.group());

    p = TokenSequencePattern.compile(
            new SequencePattern.SequencePatternExpr(
                      new SequencePattern.RepeatPatternExpr(
                              getSequencePatternExpr("[A-Za-z]+"), 0, -1),
                      getSequencePatternExpr("Mellitus", "was")));

    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus was", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile(
            new SequencePattern.SequencePatternExpr(
                      new SequencePattern.RepeatPatternExpr(
                              getSequencePatternExpr("[A-Za-z]+"), 1, -1),
                      getSequencePatternExpr("Mellitus", "was")));

    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertFalse(match);

  }

}