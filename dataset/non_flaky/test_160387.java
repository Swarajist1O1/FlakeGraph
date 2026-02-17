class DummyClass_160387 {
  @Test
  public void getBeaconState_shouldReturnEmptyWhenRootNotFound()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    SafeFuture<Optional<BeaconState>> future =
        provider.getBeaconState(data.randomBytes32().toHexString());
    final Optional<BeaconState> maybeState = future.get();
    assertThat(maybeState).isEmpty();
  }

}