class DummyClass_160364 {
  @Test
  public void shouldFailWithVersionCheckFailure(@TempDir final Path tempDir)
      throws URISyntaxException, IOException {
    final String errorString = loadAndGetErrorText("oldMetadata.json", tempDir);
    assertThat(errorString)
        .contains("Required version is " + Metadata.INTERCHANGE_VERSION.toString());
  }

}