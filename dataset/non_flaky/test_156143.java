class DummyClass_156143 {
  @Test
  public void getDependencies() {
    assertThat(ClassRenamer.v().getDependencies(), equalTo(new String[] { ClassRenamer.name }));
  }

}