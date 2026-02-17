class DummyClass_156170 {
  @Test
  public void testAllNonRelatedClassesTyping() {

    List<Typing> typingList = new ArrayList<>();
    Local x1 = new JimpleLocal("$x1", null);

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, objectType);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, stringType);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1));
    typing3.set(x1, cloneableType);
    typingList.add(typing3);

    Typing typing4 = new Typing(Arrays.asList(x1));
    typing4.set(x1, integerType);
    typingList.add(typing4);

    Typing typing5 = new Typing(Arrays.asList(x1));
    typing5.set(x1, processType);
    typingList.add(typing5);

    Typing typing6 = new Typing(Arrays.asList(x1));
    typing6.set(x1, interfaceType);
    typingList.add(typing6);

    Typing typing7 = new Typing(Arrays.asList(x1));
    typing7.set(x1, abstractType);
    typingList.add(typing7);

    Typing typing8 = new Typing(Arrays.asList(x1));
    typing8.set(x1, fatherClassType);
    typingList.add(typing8);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(7, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing2, typing3, typing4, typing5, typing6, typing7, typing8));
  }

}