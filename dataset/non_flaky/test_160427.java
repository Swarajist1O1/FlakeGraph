class DummyClass_160427 {
  @TestTemplate
  public void submitSignedBlock_shouldReturn500ForInternalError()
      throws ExecutionException, InterruptedException {
    final SignedBeaconBlock internalSignedBeaconBlock =
        dataStructureUtil.randomSignedBeaconBlock(1);
    final tech.pegasys.teku.api.schema.SignedBeaconBlock signedBeaconBlock =
        tech.pegasys.teku.api.schema.SignedBeaconBlock.create(internalSignedBeaconBlock);

    final SafeFuture<SendSignedBlockResult> failImportResult =
        completedFuture(SendSignedBlockResult.rejected(FailureReason.INTERNAL_ERROR.name()));

    when(validatorApiChannel.sendSignedBlock(any())).thenReturn(failImportResult);

    final SafeFuture<ValidatorBlockResult> validatorBlockResultSafeFuture =
        provider.submitSignedBlock(signedBeaconBlock);

    assertThat(validatorBlockResultSafeFuture.get().getResponseCode()).isEqualTo(500);
  }

}