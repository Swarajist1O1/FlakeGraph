class DummyClass_175812 {
  @Test
  public void testConvertFontToBold() {
    Label label = new Label(shellTestResource.getShell(), SWT.NONE);
    for (FontData fontData : label.getFont().getFontData()) {
      assertThat(fontData.getStyle(), is(not(SWT.BOLD)));
    }
    FontUtil.convertFontToBold(label);
    for (FontData fontData : label.getFont().getFontData()) {
      assertThat(fontData.getStyle(), is(SWT.BOLD));
    }
  }

}