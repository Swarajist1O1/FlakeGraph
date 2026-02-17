class DummyClass_160417 {
  @Test
  public void getBlockAttestations_shouldReturnAttestationsOfBlock() throws Exception {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    ChainBuilder chainBuilder = storageSystem.chainBuilder();

    ChainBuilder.BlockOptions blockOptions = ChainBuilder.BlockOptions.create();
    AttestationGenerator attestationGenerator =
        new AttestationGenerator(spec, chainBuilder.getValidatorKeys());
    tech.pegasys.teku.spec.datastructures.operations.Attestation attestation1 =
        attestationGenerator.validAttestation(bestBlock.toUnsigned(), bestBlock.getSlot());
    tech.pegasys.teku.spec.datastructures.operations.Attestation attestation2 =
        attestationGenerator.validAttestation(
            bestBlock.toUnsigned(), bestBlock.getSlot().increment());
    blockOptions.addAttestation(attestation1);
    blockOptions.addAttestation(attestation2);
    SignedBlockAndState newHead =
        storageSystem
            .chainBuilder()
            .generateBlockAtSlot(bestBlock.getSlot().plus(10), blockOptions);
    storageSystem.chainUpdater().saveBlock(newHead);
    storageSystem.chainUpdater().updateBestBlock(newHead);

    final Optional<List<Attestation>> response = provider.getBlockAttestations("head").get();
    assertThat(response).isPresent();
    assertThat(response.get())
        .containsExactly(new Attestation(attestation1), new Attestation(attestation2));
  }

}