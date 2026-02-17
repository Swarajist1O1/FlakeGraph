class DummyClass_175799 {
  @Test
  public void testFileDialogFilterSet_absolutePathInField() {
    when(field.getText()).thenReturn(basePath + "/deploy/temp/app.yaml");
    when(dialog.open()).thenReturn(null);

    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    // "basePath" is the first physically existing directory.
    verify(dialog).setFilterPath(basePath.toString());

    basePath.append("deploy").toFile().mkdir();
    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    verify(dialog).setFilterPath(basePath + "/deploy");
  }

}