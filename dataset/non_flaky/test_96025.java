class DummyClass_96025 {
  @Test
  public void testTokenSequenceMatcherAll2() throws IOException {
    String text = "DATE1 PROD1 PRICE1 PROD2 PRICE2 PROD3 PRICE3 DATE2 PROD4 PRICE4 PROD5 PRICE5 PROD6 PRICE6";
    CoreMap doc = createDocument(text);
    TokenSequencePattern p = TokenSequencePattern.compile(
        "(/DATE.*/) (?: /PROD.*/ /PRICE.*/)* (/PROD.*/) (/PRICE.*/)");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    m.setFindType(SequenceMatcher.FindType.FIND_ALL);
    // Test finding of ALL matching sequences
    boolean match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE1", m.group(1));
    assertEquals("PROD3", m.group(2));
    assertEquals("PRICE3", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE1", m.group(1));
    assertEquals("PROD2", m.group(2));
    assertEquals("PRICE2", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE1", m.group(1));
    assertEquals("PROD1", m.group(2));
    assertEquals("PRICE1", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE2", m.group(1));
    assertEquals("PROD6", m.group(2));
    assertEquals("PRICE6", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE2", m.group(1));
    assertEquals("PROD5", m.group(2));
    assertEquals("PRICE5", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE2", m.group(1));
    assertEquals("PROD4", m.group(2));
    assertEquals("PRICE4", m.group(3));
    match = m.find();
    assertFalse(match);
  }

}