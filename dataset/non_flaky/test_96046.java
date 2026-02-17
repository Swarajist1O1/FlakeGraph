class DummyClass_96046 {
  @Test
  public void testMultiPatternMatcher() throws IOException {
    CoreMap doc = createDocument(testText1);

    // Test simple sequence
    TokenSequencePattern p1 = TokenSequencePattern.compile("/Archbishop/ /of/ /Canterbury/");
    p1.setPriority(1);
    TokenSequencePattern p2 = TokenSequencePattern.compile("/[a-zA-Z]+/{1,2}  /of/ /[a-zA-Z]+/+");
    MultiPatternMatcher<CoreMap> m = new MultiPatternMatcher<CoreMap>(p2,p1);
    List<SequenceMatchResult<CoreMap>> matched = m.findNonOverlapping(doc.get(CoreAnnotations.TokensAnnotation.class));
    assertEquals(4, matched.size());
    assertEquals("first Bishop of London", matched.get(0).group());
    assertEquals("Archbishop of Canterbury", matched.get(1).group());
    assertEquals("a member of the Gregorian mission sent to England to convert the", matched.get(2).group());
    assertEquals("as Bishop of London in", matched.get(3).group());
  }

}