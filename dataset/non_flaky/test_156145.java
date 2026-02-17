class DummyClass_156145 {
  @Test
  public void getClassName() {
    assertNull(ClassRenamer.getClassName(""));
    assertNull(ClassRenamer.getClassName(null));
    assertNull(ClassRenamer.getClassName("."));
    assertEquals("ClassName", ClassRenamer.getClassName("ClassName"));
    assertEquals("Soot", ClassRenamer.getClassName("com.sable.Soot"));
    assertNull(ClassRenamer.getClassName("com.sable."));
  }

}