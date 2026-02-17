class DummyClass_96021 {
  @Test
  public void testTokenSequenceMatcherConj() throws IOException {
    CoreMap doc = createDocument(testText1);
    TokenSequencePattern p = TokenSequencePattern.compile(
                  new SequencePattern.AndPatternExpr(
                    new SequencePattern.SequencePatternExpr(
                      new SequencePattern.GroupPatternExpr(
                            new SequencePattern.RepeatPatternExpr(
                                    getNodePatternExpr("[A-Za-z]+"), 2, 2)),
                      getNodePatternExpr("of"),
                      new SequencePattern.GroupPatternExpr(
                            new SequencePattern.RepeatPatternExpr(
                                    getNodePatternExpr("[A-Za-z]+"), 1, 3, false))),
                    new SequencePattern.SequencePatternExpr(
                      new SequencePattern.GroupPatternExpr(
                        new SequencePattern.RepeatPatternExpr(
                            getNodePatternExpr(".*"), 0, -1)),
                      getNodePatternExpr("Bishop"),
                      new SequencePattern.RepeatPatternExpr(
                            getNodePatternExpr(".*"), 0, -1)
                    )));

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("first", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    // TODO: This conjunction has both a greedy and nongreedy pattern
    //  - the greedy will try to match as much as possible
    //  - while the non greedy will try to match less
    //  - currently the greedy overrides the nongreedy so we get an additional in...
    assertEquals("as Bishop of London in", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    assertEquals("as", m.group(3));
    match = m.find();
    assertFalse(match);


    // Same as before, but both non-greedy now...
    p = TokenSequencePattern.compile(
                  new SequencePattern.AndPatternExpr(
                    new SequencePattern.SequencePatternExpr(
                      new SequencePattern.GroupPatternExpr(
                            new SequencePattern.RepeatPatternExpr(
                                    getNodePatternExpr("[A-Za-z]+"), 2, 2)),
                      getNodePatternExpr("of"),
                      new SequencePattern.GroupPatternExpr(
                            new SequencePattern.RepeatPatternExpr(
                                    getNodePatternExpr("[A-Za-z]+"), 1, 3, false))),
                    new SequencePattern.SequencePatternExpr(
                      new SequencePattern.GroupPatternExpr(
                        new SequencePattern.RepeatPatternExpr(
                            getNodePatternExpr(".*"), 0, -1)),
                      getNodePatternExpr("Bishop"),
                      new SequencePattern.RepeatPatternExpr(
                            getNodePatternExpr(".*"), 0, -1, false)
                    )));

    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("first", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("as Bishop of London", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("as", m.group(3));
    match = m.find();
    assertFalse(match);


    // Same as before, but compiled from string
    p = TokenSequencePattern.compile(
            "(?: (/[A-Za-z]+/{2,2}) /of/ (/[A-Za-z]+/{1,3}?) ) & (?: (/.*/*) /Bishop/ /.*/*? )");

    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("first", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("as Bishop of London", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("as", m.group(3));
    match = m.find();
    assertFalse(match);
  }

}