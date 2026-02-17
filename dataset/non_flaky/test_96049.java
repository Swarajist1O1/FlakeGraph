class DummyClass_96049 {
  @Test
  public void testCompile() {
    String s = "(?$se \"matching\" \"this\"|\"don't\")";
    CoreMap doc = createDocument("does this do matching this");
    TokenSequencePattern p = TokenSequencePattern.compile(s);
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    //assertEquals(m.group(), "matching this");
  }

}