class DummyClass_96042 {
  @Test
  public void testTokenSequenceMatcherABs() throws IOException {
    CoreMap doc = createDocument("A A A A A A A B A A B A C A E A A A A A A A A A A A B A A A");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "/A/+ B");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("A A A A A A A B", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("A A B", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("A A A A A A A A A A A B", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "(/A/+ B)+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A B A A B", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A A A A A B", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "( A+ ( /B/+ )? )*");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("A A A A A A A B A A B A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(2, m.groupCount());
    assertEquals("A A A A A A A A A A A B A A A", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "(/A/+ /B/+ )+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A B A A B", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A A A A A B", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "(/A/+ /C/? /A/* )+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A C A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A A A A A A A A A", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(1, m.groupCount());
    assertEquals("A A A", m.group());
    match = m.find();
    assertFalse(match);
  }

}