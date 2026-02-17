class DummyClass_96870 {
  @Test
  public void testSettingOutputCharacterEncoding() throws Exception {
    SpecificCompiler compiler = createCompiler();
    // Generated file in default encoding
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    byte[] fileInDefaultEncoding = new byte[(int) this.outputFile.length()];
    FileInputStream is = new FileInputStream(this.outputFile);
    is.read(fileInDefaultEncoding);
    is.close(); //close input stream otherwise delete might fail
    if (!this.outputFile.delete()) {
      throw new IllegalStateException("unable to delete " + this.outputFile); //delete otherwise compiler might not overwrite because src timestamp hasn't changed.
    }
    // Generate file in another encoding (make sure it has different number of bytes per character)
    String differentEncoding = Charset.defaultCharset().equals(Charset.forName("UTF-16")) ? "UTF-32" : "UTF-16";
    compiler.setOutputCharacterEncoding(differentEncoding);
    compiler.compileToDestination(this.src, this.OUTPUT_DIR.getRoot());
    byte[] fileInDifferentEncoding = new byte[(int) this.outputFile.length()];
    is = new FileInputStream(this.outputFile);
    is.read(fileInDifferentEncoding);
    is.close();
    // Compare as bytes
    assertThat("Generated file should contain different bytes after setting non-default encoding",
      fileInDefaultEncoding, not(equalTo(fileInDifferentEncoding)));
    // Compare as strings
    assertThat("Generated files should contain the same characters in the proper encodings",
      new String(fileInDefaultEncoding), equalTo(new String(fileInDifferentEncoding, differentEncoding)));
  }

}