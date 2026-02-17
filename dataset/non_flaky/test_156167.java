class DummyClass_156167 {
  @Test
  public void testJavaInterfaceTyping() {

    List<Typing> typingList = new ArrayList<>();

    Local x1 = new JimpleLocal("$x1", null);

    Typing typing1 = new Typing(Arrays.asList(x1));
    typing1.set(x1, interfaceType);
    typingList.add(typing1);

    Typing typing2 = new Typing(Arrays.asList(x1));
    typing2.set(x1, integerType);
    typingList.add(typing2);

    Typing typing3 = new Typing(Arrays.asList(x1));
    typing3.set(x1, numberType);
    typingList.add(typing3);

    getTypingStrategy().minimize(typingList, new BytecodeHierarchy());

    assertEquals(2, typingList.size());
    assertThat(typingList, containsInAnyOrder(typing2, typing1));
  }

}