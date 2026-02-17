class DummyClass_160366 {
  @Test
  public void shouldImportSingleRecord(@TempDir Path tempDir)
      throws URISyntaxException, IOException {
    final File ruleFile = usingResourceFile("slashProtection.yml", tempDir);
    final SlashingProtectionImporter importer = new SlashingProtectionImporter(tempDir);
    importer.initialise(ruleFile);
    final Optional<String> maybeError = importer.updateSigningRecord(publicKey, (__) -> {});
    assertThat(maybeError).isEmpty();
    assertThat(tempDir.resolve(pubkey + ".yml").toFile()).exists();
  }

}