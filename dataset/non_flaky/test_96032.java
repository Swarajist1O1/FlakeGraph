class DummyClass_96032 {
  @Test
  public void testTokenSequenceMatcher9() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test sequence with groups
//    TokenSequencePattern p = TokenSequencePattern.compile( "(?$contextprev /.*/) (?$treat [{{treat}} & /.*/]) (?$contextnext [/.*/])");
    TokenSequencePattern p = TokenSequencePattern.compile("(?$contextprev /.*/) (?$test [{tag:NNP} & /.*/]) (?$contextnext [/.*/])");

    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(3, m.groupCount());
    assertEquals("first Bishop of", m.group());

    assertEquals("first", m.group(1));
    assertEquals("Bishop", m.group(2));
    assertEquals("of", m.group(3));
    assertEquals("first", m.group("$contextprev"));
    assertEquals("Bishop", m.group("$test"));
    assertEquals("of", m.group("$contextnext"));
    assertEquals("first", m.group(" $contextprev"));
    assertEquals("Bishop", m.group("$test "));
    assertEquals(null, m.group("$contex tnext"));

    assertEquals(3, m.start("$contextprev"));
    assertEquals(4, m.end("$contextprev"));
    assertEquals(4, m.start("$test"));
    assertEquals(5, m.end("$test"));
    assertEquals(5, m.start("$contextnext"));
    assertEquals(6, m.end("$contextnext"));
  }

}