class DummyClass_96023 {
  @Test
  public void testTokenSequenceMatcherConjAll() throws IOException {
    CoreMap doc = createDocument(testText1);
    TokenSequencePattern p = TokenSequencePattern.compile(
            "(?: (/[A-Za-z]+/{1,2}) /of/ (/[A-Za-z]+/{1,3}?) ) & (?: (/.*/*) /Bishop/ /.*/*? )");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    m.setFindType(SequenceMatcher.FindType.FIND_ALL);
    // Test finding of ALL matching sequences with conjunctions
    // todo: Not all sequences are found for some reason - missing sequences starting with just Bishop....
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
    assertEquals("Bishop of London", m.group());
    assertEquals("Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("as Bishop of London", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("as", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("as Bishop of London in", m.group());
    assertEquals("as Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    assertEquals("as", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Bishop of London", m.group());
    assertEquals("Bishop", m.group(1));
    assertEquals("London", m.group(2));
    assertEquals("", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("Bishop of London in", m.group());
    assertEquals("Bishop", m.group(1));
    assertEquals("London in", m.group(2));
    assertEquals("", m.group(3));
    match = m.find();
    assertFalse(match);
  }

}