class DummyClass_175796 {
  @Test
  public void testSetField() {
    when(field.getText()).thenReturn("");
    when(dialog.open()).thenReturn(basePath + "/sub/directory/app.yaml");

    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    verify(field).setText("sub/directory/app.yaml");
  }

}