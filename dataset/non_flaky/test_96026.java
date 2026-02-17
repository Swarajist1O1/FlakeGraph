class DummyClass_96026 {
  @Test
  public void testTokenSequenceMatcherNonOverlapping() throws IOException {
    String text = "DATE1 PROD1 PRICE1 PROD2 PRICE2 PROD3 PRICE3 DATE2 PROD4 PRICE4 PROD5 PRICE5 PROD6 PRICE6";
    CoreMap doc = createDocument(text);
    TokenSequencePattern p = TokenSequencePattern.compile(
        "(/DATE.*/) ((/PROD.*/ /PRICE.*/)+)");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE1", m.group(1));
    assertEquals("PROD1 PRICE1 PROD2 PRICE2 PROD3 PRICE3", m.group(2));
    assertEquals("PROD3 PRICE3", m.group(3));
    match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("DATE2", m.group(1));
    assertEquals("PROD4 PRICE4 PROD5 PRICE5 PROD6 PRICE6", m.group(2));
    assertEquals("PROD6 PRICE6", m.group(3));
    match = m.find();
    assertFalse(match);
  }

}