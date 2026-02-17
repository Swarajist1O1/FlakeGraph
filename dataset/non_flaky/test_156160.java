class DummyClass_156160 {
  @Test
  public void testMostCommonTypingPairs_1() {

    logger.debug("Starting Object Random Minimize");

    List<Typing> typingList = new ArrayList<>();
    Type Type1 = serializableType;
    Type Type2 = comparableType;
    Local x1 = new JimpleLocal("$x1", null);
    Typing resultTyping;

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, Type1);
    typingList.add(typing1);
    resultTyping = typing1;

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, Type2);
    typingList.add(typing2);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(2, typingList.size());
    assertEquals(resultTyping, typingList.get(0));
  }

}