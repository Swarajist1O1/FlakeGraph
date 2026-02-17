class DummyClass_160353 {
  @Test
  public void shouldReadFileWithGenesisRootDefinedSecond(@TempDir Path tempDir)
      throws URISyntaxException, IOException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    Optional<String> error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtection.yml", tempDir), log::add);
    assertThat(error).isEmpty();
    error =
        exporter.readSlashProtectionFile(
            usingResourceFile("slashProtectionWithGenesisRoot.yml", tempDir), log::add);
    assertThat(error).isEmpty();

    assertThat(log).containsExactly("Exporting " + pubkey, "Exporting " + pubkey);
  }

}