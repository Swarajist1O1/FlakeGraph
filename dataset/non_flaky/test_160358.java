class DummyClass_160358 {
  @Test
  public void shouldExportSlashProtection(@TempDir Path tempDir)
      throws IOException, URISyntaxException {
    final Path exportedFile = tempDir.resolve("exportedFile.json").toAbsolutePath();
    final SlashingProtectionIncrementalExporter exporter =
        new SlashingProtectionIncrementalExporter(tempDir);

    final Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtection.yml", tempDir), LOG::debug);
    assertThat(error).isEmpty();
    assertThat(Files.exists(exportedFile)).isFalse();
    exporter.saveToFile(exportedFile.toString(), LOG::debug);

    final String exportedData = exporter.finalise();
    final String expectedResult = resourceFileAsString("shouldExportSlashProtection.json");
    assertThat(exportedData).isEqualTo(expectedResult);
  }

}