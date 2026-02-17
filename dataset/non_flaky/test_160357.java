class DummyClass_160357 {
  @Test
  public void shouldExportSlashProtection(@TempDir Path tempDir)
      throws IOException, URISyntaxException {
    final Path exportedFile = tempDir.resolve("exportedFile.json").toAbsolutePath();
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);

    final Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtection.yml", tempDir), LOG::debug);
    assertThat(error).isEmpty();
    assertThat(Files.exists(exportedFile)).isFalse();
    exporter.saveToFile(exportedFile.toString(), LOG::debug);
    assertThat(Files.exists(exportedFile)).isTrue();
  }

}