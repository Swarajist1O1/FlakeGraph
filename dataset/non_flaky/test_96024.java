class DummyClass_96024 {
  @Test
  public void testTokenSequenceMatcherAll() throws IOException {
    CoreMap doc = createDocument(testText1);
    TokenSequencePattern p = TokenSequencePattern.compile(
            "(/[A-Za-z]+/{1,2}) /of/ (/[A-Za-z]+/{1,3}?) ");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    m.setFindType(SequenceMatcher.FindType.FIND_ALL);
    // Test finding of ALL matching sequences
    // NOTE: when using FIND_ALL greedy/reluctant modifiers are not enforced
    //       perhaps should add syntax where some of them are enforced...
    boolean match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("first Bishop of London", m.group());
    assertEquals("first Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("Bishop of London", m.group());
    assertEquals("Bishop", m.group(1));
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
    assertEquals("Archbishop of Canterbury", m.group());
    assertEquals("Archbishop", m.group(1));
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
    assertEquals("a member of the Gregorian", m.group());
    assertEquals("a member", m.group(1));
    assertEquals("the Gregorian", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("a member of the Gregorian mission", m.group());
    assertEquals("a member", m.group(1));
    assertEquals("the Gregorian mission", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("member of the", m.group());
    assertEquals("member", m.group(1));
    assertEquals("the", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("member of the Gregorian", m.group());
    assertEquals("member", m.group(1));
    assertEquals("the Gregorian", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("member of the Gregorian mission", m.group());
    assertEquals("member", m.group(1));
    assertEquals("the Gregorian mission", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("as Bishop of London", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("as Bishop of London in", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("Bishop of London", m.group());
    assertEquals("Bishop", m.group(1));
    assertEquals("London", m.group(2));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("Bishop of London in", m.group());
    assertEquals("Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    match = m.find();
    assertFalse(match);
  }

}