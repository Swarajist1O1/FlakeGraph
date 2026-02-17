class DummyClass_156144 {
  @Test
  public void getPackageName() {
    assertNull(ClassRenamer.getPackageName(""));
    assertNull(ClassRenamer.getPackageName(null));
    assertNull(ClassRenamer.getPackageName("."));
    assertNull(ClassRenamer.getPackageName("ClassName"));
    assertEquals("com.sable", ClassRenamer.getPackageName("com.sable.Soot"));
  }

}