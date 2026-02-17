class DummyClass_96052 {
  @Test
  public void testCaseInsensitive1(){
    Env env = TokenSequencePattern.getNewEnv();
    env.setDefaultStringPatternFlags(Pattern.CASE_INSENSITIVE);
    env.setDefaultStringMatchFlags(NodePattern.CASE_INSENSITIVE);
    String s = "for /President/";
    CoreMap doc = createDocument("for president");
    TokenSequencePattern p = TokenSequencePattern.compile(env, s);
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
  }

}