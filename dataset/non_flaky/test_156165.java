class DummyClass_156165 {
  @Test
  public void testAbstractInterfaceTyping() {

    List<Typing> typingList = new ArrayList<>();

    Local x1 = new JimpleLocal("$x1", null);

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, interfaceType);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, abstractClass_Interface2Type);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1));
    typing3.set(x1, class_AbstractInterfaceClassType);
    typingList.add(typing3);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(1, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing3));
  }

}