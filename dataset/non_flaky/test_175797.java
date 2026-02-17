class DummyClass_175797 {
  @Test
  public void testSetField_userSuppliesPathOutsideBase() {
    when(field.getText()).thenReturn("");
    when(dialog.open()).thenReturn("/path/outside/base/app.yaml");

    new RelativeFileFieldSetter(field, new Path("/base/path"), dialog).widgetSelected(event);
    verify(field).setText("../../path/outside/base/app.yaml");
  }

}