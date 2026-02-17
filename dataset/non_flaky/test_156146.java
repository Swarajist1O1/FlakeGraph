class DummyClass_156146 {
  @Test
  public void getOrAddNewName_cachingName() {
    ClassRenamer.v().setRemovePackages(false);
    ClassRenamer.v().setRenamePackages(false);

    final String newName = ClassRenamer.v().getOrAddNewName(null, "ClassName");
    assertThat(newName, not(containsString(".")));

    Map<String, String> mapping = ClassRenamer.v().getClassNameMapping((pOldName, pNewName) -> pOldName.equals("ClassName"));
    assertThat(mapping, hasEntry("ClassName", newName));
    assertThat(mapping.size(), equalTo(1));

    assertThat(ClassRenamer.v().getOrAddNewName(null, "ClassName"), equalTo(newName));

    mapping = ClassRenamer.v().getClassNameMapping((pOldName, pNewName) -> pOldName.equals("ClassName"));
    assertThat(mapping, hasEntry("ClassName", newName));
    assertThat(mapping.size(), equalTo(1));
  }

}