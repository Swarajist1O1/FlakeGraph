class DummyClass_160388 {
  @Test
  public void getBeaconState_shouldFindHeadState() throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    SafeFuture<Optional<BeaconState>> future = provider.getBeaconState("head");
    final Optional<BeaconState> maybeState = future.get();
    assertThat(maybeState.get().asInternalBeaconState(spec).hashTreeRoot())
        .isEqualTo(beaconStateInternal.hashTreeRoot());
  }

}