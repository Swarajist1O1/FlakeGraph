class DummyClass_96037 {
  @Test
  public void testTokenSequenceMatcherNumber() throws IOException {
    CoreMap doc = createDocument("It happened on January 3, 2002");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "[ { word::IS_NUM } ]+");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("3", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word>=2002 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word>2002 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertFalse(match);

    // Check no {} with or
    p = TokenSequencePattern.compile( "[ word > 2002 | word==2002 ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    // Check no {} with and
    p = TokenSequencePattern.compile( "[ word>2002 & word==2002 ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word>2000 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word<=2002 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("3", m.group());
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word<2002 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("3", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { word==2002 } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { ner:DATE } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("January 3, 2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { ner::NOT_NIL } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("January 3, 2002", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ { ner::IS_NIL } ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("It happened on", m.group());
    match = m.find();
    assertFalse(match);

    p = TokenSequencePattern.compile( "[ {{ word=~/2002/ }} ]+");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("2002", m.group());
    match = m.find();
    assertFalse(match);
  }

}