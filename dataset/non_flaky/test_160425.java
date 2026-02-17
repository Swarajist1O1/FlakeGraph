class DummyClass_160425 {
  @TestTemplate
  public void submitSignedBlock_shouldReturn200ForSuccess()
      throws ExecutionException, InterruptedException {
    final SignedBeaconBlock internalSignedBeaconBlock =
        dataStructureUtil.randomSignedBeaconBlock(1);
    final tech.pegasys.teku.api.schema.SignedBeaconBlock signedBeaconBlock =
        tech.pegasys.teku.api.schema.SignedBeaconBlock.create(internalSignedBeaconBlock);

    final SafeFuture<SendSignedBlockResult> successImportResult =
        completedFuture(SendSignedBlockResult.success(internalSignedBeaconBlock.getRoot()));

    when(validatorApiChannel.sendSignedBlock(any())).thenReturn(successImportResult);

    final SafeFuture<ValidatorBlockResult> validatorBlockResultSafeFuture =
        provider.submitSignedBlock(signedBeaconBlock);

    assertThat(validatorBlockResultSafeFuture.get().getResponseCode()).isEqualTo(200);
  }

}