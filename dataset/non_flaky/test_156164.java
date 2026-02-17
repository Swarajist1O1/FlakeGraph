class DummyClass_156164 {
  @Test
  public void testHugeCommonTypingPair() {

    List<Typing> typingList = new ArrayList<>();

    Type Type1 = serializableType;
    Type Type2 = comparableType;
    Local x1 = new JimpleLocal("$x1", null);
    Local x2 = new JimpleLocal("$x2", null);
    Local x3 = new JimpleLocal("$x3", null);

    Typing typing1 = new Typing(Arrays.asList(x1, x2, x3));
    typing1.set(x1, Type1);
    typing1.set(x2, Type1);
    typing1.set(x3, Type1);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1, x2, x3));
    typing2.set(x1, Type2);
    typing2.set(x2, Type1);
    typing2.set(x3, Type1);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1, x2, x3));
    typing3.set(x1, Type1);
    typing3.set(x2, Type2);
    typing3.set(x3, Type1);
    typingList.add(typing3);

    Typing typing4 = new Typing(Arrays.asList(x1, x2, x3));
    typing4.set(x1, Type1);
    typing4.set(x2, Type1);
    typing4.set(x3, Type2);
    typingList.add(typing4);

    Typing typing5 = new Typing(Arrays.asList(x1, x2, x3));
    typing5.set(x1, Type2);
    typing5.set(x2, Type2);
    typing5.set(x3, Type1);
    typingList.add(typing5);

    Typing typing6 = new Typing(Arrays.asList(x1, x2, x3));
    typing6.set(x1, Type2);
    typing6.set(x2, Type1);
    typing6.set(x3, Type2);
    typingList.add(typing6);

    Typing typing7 = new Typing(Arrays.asList(x1, x2, x3));
    typing7.set(x1, Type1);
    typing7.set(x2, Type2);
    typing7.set(x3, Type2);
    typingList.add(typing7);

    Typing typing8 = new Typing(Arrays.asList(x1, x2, x3));
    typing8.set(x1, Type2);
    typing8.set(x2, Type2);
    typing8.set(x3, Type2);
    typingList.add(typing8);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(8, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing1, typing2, typing3, typing4, typing5, typing6, typing7, typing8));
  }

}