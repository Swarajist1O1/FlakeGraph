class DummyClass_156142 {
  @Test
  public void getName() {
    assertThat(ClassRenamer.v().getName(), equalTo(ClassRenamer.name));
  }

}