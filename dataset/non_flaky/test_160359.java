class DummyClass_160359 {
  @Test
  public void shouldNotUpdateFilesWithInvalidPubkeys(@TempDir Path tempDir) throws IOException {
    setupPathForTest(tempDir, Map.of("a.yml", Optional.of(validatorSigningRecord)));
    SlashingProtectionRepairer repairer =
        SlashingProtectionRepairer.create(subCommandLogger, tempDir, true);
    assertThat(repairer.hasUpdates()).isFalse();
    verify(subCommandLogger).display(" --- a.yml - invalid public key - ignoring file");

    repairer.updateRecords(UInt64.MAX_VALUE, UInt64.MAX_VALUE);
    verifyNoMoreInteractions(subCommandLogger);

    assertThat(fileContents(tempDir.resolve("a.yml")))
        .isEqualTo(Optional.of(validatorSigningRecord));
  }

}