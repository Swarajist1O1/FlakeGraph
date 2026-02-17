class DummyClass_150208 {
  @Test
  public void test() throws Exception {
    StringExpr.Finder pattern = compile("pattern");
    assertNotNull(pattern);

    StringExpr.Finder patternOneChar = compile("g");
    assertNotNull(patternOneChar);

    StringExpr.Finder patternZero = compile("");
    assertNotNull(patternZero);

    String input1 = "string that contains a patterN...";
    String input2 = "string that contains a pattern...";
    String input3 = "pattern at the start of a string";
    String input4 = "string that ends with a pattern";

    assertEquals("Testing invalid match", -1, find(pattern, input1));
    assertEquals("Testing valid match", 23, find(pattern, input2));
    assertEquals("Testing single-character match", 5, find(patternOneChar, input1));
    assertEquals("Testing zero-length pattern", 0, find(patternZero, input1));
    assertEquals("Testing match at start of string", 0, find(pattern, input3));
    assertEquals("Testing match at end of string", 24, find(pattern, input4));
  }

}