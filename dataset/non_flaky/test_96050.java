class DummyClass_96050 {
  @Test
  public void testBindingCompile(){
    Env env = TokenSequencePattern.getNewEnv();
    env.bind("wordname",CoreAnnotations.TextAnnotation.class);
    String s = "[wordname:\"name\"]{1,2}";
    TokenSequencePattern p = TokenSequencePattern.compile(env, s);
  }

}