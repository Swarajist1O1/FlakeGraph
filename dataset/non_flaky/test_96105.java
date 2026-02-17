class DummyClass_96105 {
  @Test
  public void testSerializeLanguage() {
    testAnnotators("tokenize,ssplit,parse");
    testAnnotators("tokenize,ssplit,pos,depparse");
  }

}