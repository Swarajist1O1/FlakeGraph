class DummyClass_160410 {
  @Test
  public void getStateFinalityCheckpoints_shouldGetCheckpointsAfterFinalized()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, mockCombinedChainDataClient);
    final tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState internalState =
        data.randomBeaconState(UInt64.valueOf(42));
    final FinalityCheckpointsResponse expected =
        new FinalityCheckpointsResponse(
            new tech.pegasys.teku.api.schema.Checkpoint(
                internalState.getPrevious_justified_checkpoint()),
            new tech.pegasys.teku.api.schema.Checkpoint(
                internalState.getCurrent_justified_checkpoint()),
            new tech.pegasys.teku.api.schema.Checkpoint(internalState.getFinalized_checkpoint()));

    when(mockCombinedChainDataClient.getBestState()).thenReturn(Optional.of(internalState));
    assertThat(provider.getStateFinalityCheckpoints("head").get().get()).isEqualTo(expected);
    verify(mockCombinedChainDataClient).getBestState();
  }

}