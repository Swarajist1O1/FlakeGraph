class DummyClass_160350 {
  @Test
  public void shouldReadSlashingProtectionFile_withEmptyGenesisValidatorsRoot(@TempDir Path tempDir)
      throws IOException, URISyntaxException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtection.yml", tempDir), log::add);
    assertThat(log).containsExactly("Exporting " + pubkey);
    assertThat(error).isEmpty();

    final SlashingProtectionInterchangeFormat parsedData =
        jsonProvider.jsonToObject(
            exporter.getPrettyJson(), SlashingProtectionInterchangeFormat.class);
    final SlashingProtectionInterchangeFormat expectedData = getExportData(null, 327, 51, 1741);
    assertThat(parsedData).isEqualTo(expectedData);
  }

}