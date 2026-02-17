class DummyClass_96043 {
  @Test
  public void testTokenSequenceMatcherMultiNodePattern() throws IOException {
    CoreMap doc = createDocument("blah four-years blah blah four - years");

    // Test sequence with groups
    CoreMapNodePattern nodePattern  = CoreMapNodePattern.valueOf("four\\s*-?\\s*years");
    SequencePattern.MultiNodePatternExpr expr = new SequencePattern.MultiNodePatternExpr(
            new MultiCoreMapNodePattern(nodePattern));
    TokenSequencePattern p = TokenSequencePattern.compile(expr);
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four-years", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four - years", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile("(?m) /four\\s*-?\\s*years/");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four-years", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four - years", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile("(?m){2,3} /four\\s*-?\\s*years/");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four - years", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "(?m){1,2} /four\\s*-?\\s*years/");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four-years", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile("(?m){1,3} /four\\s*-?\\s*years/ ==> &annotate( { ner=YEAR } )");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four-years", m.group());
    p.getAction().apply(m, 0);
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four - years", m.group());
    SequenceMatchResult<CoreMap> res = p.getAction().apply(m, 0);
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { ner:YEAR } ]+");
    m = p.getMatcher(res.elements());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four-years", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("four - years", m.group());
    match = m.find();
    assertFalse(match);
  }

}