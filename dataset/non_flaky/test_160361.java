class DummyClass_160361 {
  @Test
  public void shouldUpdateInvalidFiles(@TempDir Path tempDir) throws IOException {
    setupPathForTest(tempDir, testData);
    SlashingProtectionRepairer repairer =
        SlashingProtectionRepairer.create(subCommandLogger, tempDir, false);
    assertThat(repairer.hasUpdates()).isTrue();

    final UInt64 blockSlot = UInt64.valueOf(1023999);
    final UInt64 attestationEpoch = UInt64.valueOf(2344);
    repairer.updateRecords(blockSlot, attestationEpoch);

    final Optional<ValidatorSigningRecord> defaultRecord =
        Optional.of(
            new ValidatorSigningRecord(null, blockSlot, attestationEpoch, attestationEpoch));

    assertThat(fileContents(tempDir.resolve(keys.get(0)))).isEqualTo(defaultRecord);
    assertThat(fileContents(tempDir.resolve(keys.get(1)))).isEqualTo(testData.get(keys.get(1)));
    assertThat(fileContents(tempDir.resolve(keys.get(2)))).isEqualTo(testData.get(keys.get(2)));
    assertThat(fileContents(tempDir.resolve(keys.get(3)))).isEqualTo(testData.get(keys.get(3)));
  }

}