class DummyClass_156147 {
  @Test
  public void getOrAddNewName_cachingPackage() {
    ClassRenamer.v().setRemovePackages(false);
    ClassRenamer.v().setRenamePackages(false);

    final String newName = ClassRenamer.v().getOrAddNewName("pac.age", "ClassName");
    assertThat(newName, allOf(startsWith("pac.age."), not(endsWith("ClassName"))));
    assertThat(newName.split("\\.").length, equalTo(3));

    assertThat(ClassRenamer.v().getOrAddNewName("pac.age", "ClassName"), equalTo(newName));
  }

}