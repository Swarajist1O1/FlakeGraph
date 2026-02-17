class DummyClass_160355 {
  @Test
  public void shouldRequirePubkeyInFilename(@TempDir Path tempDir) throws URISyntaxException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    final Optional<String> error =
        exporter.readSlashProtectionFile(
            new File(Resources.getResource("slashProtectionWithGenesisRoot.yml").toURI()),
            LOG::debug);
    assertThat(error.orElse(""))
        .contains("Public key in file slashProtectionWithGenesisRoot.yml does not appear valid.");
  }

}