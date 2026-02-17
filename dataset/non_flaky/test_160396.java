class DummyClass_160396 {
  @Test
  public void getStateRoot_shouldGetRootAtGenesis()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    final Optional<tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState> state =
        combinedChainDataClient.getStateAtSlotExact(ZERO).get();
    final Optional<Root> maybeStateRoot = provider.getStateRoot("genesis").get();
    assertThat(maybeStateRoot).isPresent();
    assertThat(maybeStateRoot.orElseThrow().root).isEqualTo(state.orElseThrow().hashTreeRoot());
  }

}