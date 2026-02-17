class DummyClass_156149 {
  @Test
  public void getOrAddNewName_renamePackage() {
    ClassRenamer.v().setRemovePackages(false);
    ClassRenamer.v().setRenamePackages(true);

    final String newName = ClassRenamer.v().getOrAddNewName("pac.age.getOrAddNewName_renamePackage", "ClassName");
    assertThat(newName, allOf(not(startsWith("pac.age.getOrAddNewName_renamePackage.")), not(endsWith("ClassName"))));
    assertThat(newName.split("\\.").length, equalTo(4));

    assertThat(ClassRenamer.v().getOrAddNewName("pac.age.getOrAddNewName_renamePackage", "ClassName"), equalTo(newName));
  }

}