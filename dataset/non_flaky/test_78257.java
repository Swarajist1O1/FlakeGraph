class DummyClass_78257 {
  @Test
  public void testSetReadable() throws Exception {
    SetState<String> value = underTest.state(NAMESPACE_1, STRING_SET_ADDR);

    // test contains
    ReadableState<Boolean> readable = value.contains("A");
    value.add("A");
    assertFalse(readable.read());

    // test addIfAbsent
    value.addIfAbsent("B");
    assertTrue(value.contains("B").read());
  }

}