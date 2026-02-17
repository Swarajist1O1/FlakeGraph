class DummyClass_175798 {
  @Test
  public void testFileDialogFilterSet_relativePathInField() {
    when(field.getText()).thenReturn("src/main/appengine/app.yaml");
    when(dialog.open()).thenReturn(null);

    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    // "basePath" is the first physically existing directory.
    verify(dialog).setFilterPath(basePath.toString());

    basePath.append("src").toFile().mkdir();
    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    verify(dialog).setFilterPath(basePath + "/src");
  }

}