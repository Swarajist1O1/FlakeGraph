class DummyClass_160362 {
  @Test
  public void shouldFailWithParseError(@TempDir final Path tempDir)
      throws URISyntaxException, IOException {
    final String errorString = loadAndGetErrorText("minimal_invalidKey.json", tempDir);
    assertThat(errorString).startsWith("Failed to load data");
  }

}