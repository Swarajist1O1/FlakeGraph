class DummyClass_156148 {
  @Test
  public void getOrAddNewName_nullClassName() {
    ClassRenamer.v().setRemovePackages(false);
    ClassRenamer.v().setRenamePackages(false);

    final String newName = ClassRenamer.v().getOrAddNewName("pac.age", null);
    assertThat(newName, startsWith("pac.age."));
    assertThat(newName.split("\\.").length, equalTo(3));

    assertThat(ClassRenamer.v().getOrAddNewName("pac.age", null), not(equalTo(newName)));
  }

}