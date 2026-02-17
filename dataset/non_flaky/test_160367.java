class DummyClass_160367 {
  @Test
  public void shouldExportAndImportFile(@TempDir Path tempDir)
      throws IOException, URISyntaxException {
    final Path exportedFile = tempDir.resolve("exportedFile.json").toAbsolutePath();

    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    final File ruleFile = usingResourceFile("slashProtection.yml", tempDir);
    final Optional<String> exportError = exporter.readSlashProtectionFile(ruleFile, LOG::debug);
    final String originalFileContent = Files.readString(ruleFile.toPath());
    assertThat(exportError).isEmpty();

    assertThat(Files.exists(ruleFile.toPath())).isTrue();
    assertThat(Files.exists(exportedFile)).isFalse();
    exporter.saveToFile(exportedFile.toString(), LOG::debug);
    ruleFile.delete();
    assertThat(Files.exists(exportedFile)).isTrue();
    assertThat(Files.exists(ruleFile.toPath())).isFalse();

    SlashingProtectionImporter importer = new SlashingProtectionImporter(tempDir);
    importer.initialise(new File(exportedFile.toString()));
    final Map<BLSPublicKey, String> errors = importer.updateLocalRecords((__) -> {});
    assertThat(errors).isEmpty();
    assertThat(Files.exists(ruleFile.toPath())).isTrue();

    assertThat(originalFileContent).isEqualTo(Files.readString(ruleFile.toPath()));
  }

}