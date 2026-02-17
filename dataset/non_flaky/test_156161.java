class DummyClass_156161 {
  @Test
  public void testMostCommonTypingPairs_2() {

    logger.debug("Starting Object Random Minimize");

    List<Typing> typingList = new ArrayList<>();

    Type Type1 = serializableType;
    Type Type2 = comparableType;
    Type Type3 = numberType;
    Local x1 = new JimpleLocal("$x1", null);

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, Type1);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, Type2);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1));
    typing3.set(x1, Type3);
    typingList.add(typing3);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(2, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing2, typing3));
  }

}