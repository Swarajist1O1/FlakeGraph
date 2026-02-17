class DummyClass_160363 {
  @Test
  public void shouldFailWithInvalidJson(@TempDir final Path tempDir)
      throws URISyntaxException, IOException {
    final String errorString = loadAndGetErrorText("invalid_json.json", tempDir);
    assertThat(errorString).startsWith("Json does not appear valid");
  }

}