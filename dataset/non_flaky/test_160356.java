class DummyClass_160356 {
  @Test
  public void shouldPrintIfFileCannotBeRead(@TempDir Path tempDir)
      throws URISyntaxException, IOException {
    final SlashingProtectionExporter exporter = new SlashingProtectionExporter(tempDir);
    final File file = usingResourceFile("slashProtection.yml", tempDir);
    OSUtils.makeNonReadable(file.toPath());
    // It's not always possible to remove read permissions from a file
    assumeThat(file.canRead()).describedAs("Can read file %s", file).isFalse();
    final Optional<String> error = exporter.readSlashProtectionFile(file, LOG::debug);
    assertThat(error.orElse("")).startsWith("Failed to read from file");
  }

}