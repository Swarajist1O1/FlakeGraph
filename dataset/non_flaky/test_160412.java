class DummyClass_160412 {
  @Test
  public void getStateSyncCommittees_shouldReturnEmptyListBeforeAltair()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState internalState =
        data.randomBeaconState();
    when(mockCombinedChainDataClient.getBestState()).thenReturn(Optional.of(internalState));

    final SafeFuture<Optional<StateSyncCommittees>> future =
        provider.getStateSyncCommittees("head", Optional.empty());
    assertThat(future.get().get()).isEqualTo(new StateSyncCommittees(List.of(), List.of()));
  }

}