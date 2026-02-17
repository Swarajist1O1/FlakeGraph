class DummyClass_156150 {
  @Test
  public void getOrAddNewName_renamePackage_nullPackage() {
    ClassRenamer.v().setRemovePackages(false);
    ClassRenamer.v().setRenamePackages(true);

    final String newName = ClassRenamer.v().getOrAddNewName(null, "ClassName");
    assertThat(newName, allOf(not(endsWith("ClassName")), not(containsString("."))));

    final String newName0 = ClassRenamer.v().getOrAddNewName(null, "ClassName");
    assertThat(newName0, equalTo(newName)); // package names and class names are equal

    final String newName1 = ClassRenamer.v().getOrAddNewName(null, "ClassName1");
    assertThat(newName1, not(equalTo(newName)));
    assertThat(newName1.split("\\.").length, equalTo(2));
    assertThat(newName.split("\\.")[0], equalTo(newName.split("\\.")[0])); // package names are equal
  }

}