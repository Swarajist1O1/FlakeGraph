class DummyClass_160351 {
  @Test
  public void shouldReadSlashingProtectionFile_withGenesisValidatorsRoot(@TempDir Path tempDir)
      throws IOException, URISyntaxException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtectionWithGenesisRoot.yml", tempDir), log::add);
    assertThat(log).containsExactly("Exporting " + pubkey);
    assertThat(error).isEmpty();

    final SlashingProtectionInterchangeFormat parsedData =
        jsonProvider.jsonToObject(
            exporter.getPrettyJson(), SlashingProtectionInterchangeFormat.class);
    final SlashingProtectionInterchangeFormat expectedData =
        getExportData(validatorsRoot, 327, 51, 1741);
    assertThat(parsedData).isEqualTo(expectedData);
  }

}