class DummyClass_160426 {
  @TestTemplate
  public void submitSignedBlock_shouldReturn202ForInvalidBlock() {
    final SignedBeaconBlock internalSignedBeaconBlock =
        dataStructureUtil.randomSignedBeaconBlock(1);
    final tech.pegasys.teku.api.schema.SignedBeaconBlock signedBeaconBlock =
        tech.pegasys.teku.api.schema.SignedBeaconBlock.create(internalSignedBeaconBlock);
    final AtomicInteger failReasonCount = new AtomicInteger();

    Stream.of(FailureReason.values())
        .filter(failureReason -> !failureReason.equals(FailureReason.INTERNAL_ERROR))
        .forEach(
            failureReason -> {
              failReasonCount.getAndIncrement();

              final SafeFuture<SendSignedBlockResult> failImportResult =
                  completedFuture(SendSignedBlockResult.notImported(failureReason.name()));

              when(validatorApiChannel.sendSignedBlock(any())).thenReturn(failImportResult);

              final SafeFuture<ValidatorBlockResult> validatorBlockResultSafeFuture =
                  provider.submitSignedBlock(signedBeaconBlock);

              try {
                assertThat(validatorBlockResultSafeFuture.get().getResponseCode()).isEqualTo(202);
              } catch (final Exception e) {
                fail("Exception while executing test.");
              }
            });

    // Assert that the check has run over each FailureReason except the 500.
    assertThat(failReasonCount.get()).isEqualTo(FailureReason.values().length - 1);
  }

}