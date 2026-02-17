class DummyClass_96028 {
  @Test
  public void testTokenSequenceMatcher5() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test simple sequence
    TokenSequencePattern p = TokenSequencePattern.compile(" [ { word:\"Archbishop\" } ]  [ { word:\"of\" } ]  [ { word:\"Canterbury\" } ]");
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


    p = TokenSequencePattern.compile(" [ \"Archbishop\" ]  [ \"of\"  ]  [ \"Canterbury\"  ]");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
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
    p = TokenSequencePattern.compile(" [ \"Archbishop\"] [\"of\"] [\"Canterbury\"] |  [ \"Bishop\" ] [ \"of\" ]  [ \"London\" ] ");
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

  }

}