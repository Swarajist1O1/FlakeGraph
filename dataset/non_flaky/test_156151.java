class DummyClass_156151 {
  @Test
  public void getOrAddNewName_removePackage() {
    ClassRenamer.v().setRemovePackages(true);

    String newName = ClassRenamer.v().getOrAddNewName("a.b.c", "ClassName");
    assertThat(newName, allOf(not(endsWith("ClassName")), not(containsString("."))));

    String packageName = "a.b.c";
    for (int i = 0; i < 100; i++) {
      packageName = packageName + ".p" + i;
      newName = ClassRenamer.v().getOrAddNewName(packageName, "ClassName");
      assertThat(newName, allOf(not(endsWith("ClassName")), not(containsString("."))));
    }
  }

}