class DummyClass_175795 {
  @Test
  public void testFileDialogCanceled() {
    when(field.getText()).thenReturn("");
    when(dialog.open()).thenReturn(null /* means canceled */);

    new RelativeFileFieldSetter(field, basePath, dialog).widgetSelected(event);
    verify(field, never()).setText(anyString());
  }

}