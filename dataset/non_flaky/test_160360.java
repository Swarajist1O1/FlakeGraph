class DummyClass_160360 {
  @Test
  public void shouldUpdateValidAndInvalidFiles(@TempDir Path tempDir) throws IOException {
    setupPathForTest(tempDir, testData);
    SlashingProtectionRepairer repairer =
        SlashingProtectionRepairer.create(subCommandLogger, tempDir, true);
    assertThat(repairer.hasUpdates()).isTrue();

    final UInt64 blockSlot = UInt64.valueOf(1023999);
    final UInt64 attestationEpoch = UInt64.valueOf(2344);
    repairer.updateRecords(blockSlot, attestationEpoch);

    final Optional<ValidatorSigningRecord> defaultRecord =
        Optional.of(
            new ValidatorSigningRecord(null, blockSlot, attestationEpoch, attestationEpoch));

    assertThat(fileContents(tempDir.resolve(keys.get(0)))).isEqualTo(defaultRecord);
    // all original values were lower, so the entire file should get updated
    assertThat(fileContents(tempDir.resolve(keys.get(1)))).isEqualTo(defaultRecord);
    // sourceAttestation changed, but other values were higher
    assertThat(fileContents(tempDir.resolve(keys.get(2))))
        .isEqualTo(
            optionalSigningRecord(UInt64.valueOf(1024000), attestationEpoch, UInt64.valueOf(2345)));
    // all original values were better
    assertThat(fileContents(tempDir.resolve(keys.get(3)))).isEqualTo(testData.get(keys.get(3)));
  }

}