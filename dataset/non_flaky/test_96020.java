class DummyClass_96020 {
  @Test
  public void testTokenSequenceMatcher3() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile(
        new SequencePattern.SequencePatternExpr(
            new SequencePattern.GroupPatternExpr(
                new SequencePattern.RepeatPatternExpr(
                    getSequencePatternExpr("[A-Za-z]+"), 1, 2)),
            getNodePatternExpr("of"),
            new SequencePattern.GroupPatternExpr(
                new SequencePattern.RepeatPatternExpr(
                    getSequencePatternExpr("[A-Za-z]+"), 1, 3))));

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("third Archbishop of Canterbury", m.group());
    assertEquals("third Archbishop", m.group(1));
    assertEquals("Canterbury", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("a member of the Gregorian mission", m.group());
    assertEquals("a member", m.group(1));
    assertEquals("the Gregorian mission", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("as Bishop of London in", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile(
        new SequencePattern.SequencePatternExpr(
            new SequencePattern.GroupPatternExpr(
                new SequencePattern.RepeatPatternExpr(
                    getNodePatternExpr("[A-Za-z]+"), 2, 2)),
            getNodePatternExpr("of"),
            new SequencePattern.GroupPatternExpr(
                new SequencePattern.RepeatPatternExpr(
                    getNodePatternExpr("[A-Za-z]+"), 1, 3, false))));

    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("third Archbishop of Canterbury", m.group());
    assertEquals("third Archbishop", m.group(1));
    assertEquals("Canterbury", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("a member of the", m.group());
    assertEquals("a member", m.group(1));
    assertEquals("the", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("as Bishop of London", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertFalse(match);
  }

}