class DummyClass_96053 {
  @Test
  public void testCaseInsensitive2(){
    Env env = TokenSequencePattern.getNewEnv();
    env.setDefaultStringPatternFlags(Pattern.CASE_INSENSITIVE);
    env.setDefaultStringMatchFlags(NodePattern.CASE_INSENSITIVE);

    String s = "for president";
    CoreMap doc = createDocument("for President");

    TokenSequencePattern p = TokenSequencePattern.compile(env, s);
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
  }

}