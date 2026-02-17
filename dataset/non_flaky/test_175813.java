class DummyClass_175813 {
  @Test
  public void testConvertFontToItalic() {
    Label label = new Label(shellTestResource.getShell(), SWT.NONE);
    for (FontData fontData : label.getFont().getFontData()) {
      assertThat(fontData.getStyle(), is(not(SWT.ITALIC)));
    }
    FontUtil.convertFontToItalic(label);
    for (FontData fontData : label.getFont().getFontData()) {
      assertThat(fontData.getStyle(), is(SWT.ITALIC));
    }
  }

}