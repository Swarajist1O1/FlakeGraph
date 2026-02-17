class DummyClass_156169 {
  @Test
  public void testAllRelatedClassesTyping() {

    List<Typing> typingList = new ArrayList<>();
    Local x1 = new JimpleLocal("$x1", null);

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, objectType);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, stringType);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1));
    typing3.set(x1, comparableType);
    typingList.add(typing3);

    Typing typing4 = new Typing(Arrays.asList(x1));
    typing4.set(x1, abstractClass_Interface2Type);
    typingList.add(typing4);

    Typing typing5 = new Typing(Arrays.asList(x1));
    typing5.set(x1, class_AbstractInterfaceClassType);
    typingList.add(typing5);

    Typing typing6 = new Typing(Arrays.asList(x1));
    typing6.set(x1, abstractClass_Interface1Type);
    typingList.add(typing6);

    Typing typing7 = new Typing(Arrays.asList(x1));
    typing7.set(x1, class_InterfaceType);
    typingList.add(typing7);

    Typing typing8 = new Typing(Arrays.asList(x1));
    typing8.set(x1, abstractType);
    typingList.add(typing8);

    Typing typing9 = new Typing(Arrays.asList(x1));
    typing9.set(x1, class_AbstractType);
    typingList.add(typing9);

    Typing typing10 = new Typing(Arrays.asList(x1));
    typing10.set(x1, fatherClassType);
    typingList.add(typing10);

    Typing typing11 = new Typing(Arrays.asList(x1));
    typing11.set(x1, childClassType);
    typingList.add(typing11);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(5, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing2, typing5, typing7, typing9, typing11));
  }

}