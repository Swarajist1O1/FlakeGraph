class DummyClass_160354 {
  @Test
  public void shouldNotAcceptDifferentGenesisValidatorsRoot(@TempDir Path tempDir)
      throws URISyntaxException, IOException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtectionWithGenesisRoot2.yml", tempDir), LOG::debug);
    assertThat(error).isEmpty();
    error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtectionWithGenesisRoot.yml", tempDir), LOG::debug);
    assertThat(error.orElse("")).startsWith("The genesisValidatorsRoot of");
  }

}