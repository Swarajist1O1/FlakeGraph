class DummyClass_156162 {
  @Test
  public void testMostCommonTypingPairs_3() {

    List<Typing> typingList = new ArrayList<>();
    Type Type1 = randomAccessType;
    Type Type2 = listType;
    Type Type3 = abstractListType;
    Type Type4 = objectType;
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

    Typing typing4 = new Typing(Arrays.asList(x1));
    typing4.set(x1, Type4);
    typingList.add(typing4);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(2, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing1, typing3));
  }

}