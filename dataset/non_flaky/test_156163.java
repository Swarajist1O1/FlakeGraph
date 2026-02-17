class DummyClass_156163 {
  @Test
  public void testMostCommonTypingPairs_4() {

    List<Typing> typingList = new ArrayList<>();

    Type Type1 = cloneableType;
    Type Type2 = serializableType;
    Type Type3 = abstractMapType;

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

    assertEquals(3, typingList.size());

  }

}