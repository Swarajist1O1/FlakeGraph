class DummyClass_160365 {
  @Test
  public void shouldFailIfMetadataNotPresent(@TempDir final Path tempDir)
      throws IOException, URISyntaxException {
    final String errorString = loadAndGetErrorText("signedBlock.json", tempDir);
    assertThat(errorString).contains("does not appear to have metadata");
  }

}