class DummyClass_96018 {
  @Test
  public void testTokenSequenceMatcher1() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test simple sequence
    TokenSequencePattern p = TokenSequencePattern.compile(getSequencePatternExpr("Archbishop", "of", "Canterbury"));
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals("Archbishop of Canterbury", m.group());
    match = m.find();
    assertFalse(match);

    m.reset();
    match = m.find();
    assertTrue(match);
    assertEquals("Archbishop of Canterbury", m.group());

    m.reset();
    match = m.matches();
    assertFalse(match);

    // Test sequence with or
    p = TokenSequencePattern.compile(
            new SequencePattern.OrPatternExpr(
                    getSequencePatternExpr("Archbishop", "of", "Canterbury"),
                    getSequencePatternExpr("Bishop", "of", "London")
            ));
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Bishop of London", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Archbishop of Canterbury", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Bishop of London", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile(
              new SequencePattern.SequencePatternExpr(
                    SequencePattern.SEQ_BEGIN_PATTERN_EXPR,
                    getSequencePatternExpr("Archbishop", "of", "Canterbury")
            ));
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile(
            new SequencePattern.SequencePatternExpr(
                    SequencePattern.SEQ_BEGIN_PATTERN_EXPR,
                    getSequencePatternExpr("Mellitus", "was", "the")
            ));
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("Mellitus was the", m.group());
    match = m.find();
    assertFalse(match);


    p = TokenSequencePattern.compile(
            new SequencePattern.SequencePatternExpr(
                    getSequencePatternExpr("Archbishop", "of", "Canterbury"),
                    SequencePattern.SEQ_END_PATTERN_EXPR
                    ));
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile(
            new SequencePattern.SequencePatternExpr(
                    getSequencePatternExpr("London", "in", "604", "."),
                    SequencePattern.SEQ_END_PATTERN_EXPR
                    ));
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("London in 604.", m.group());
    match = m.find();
    assertFalse(match);
  }

}