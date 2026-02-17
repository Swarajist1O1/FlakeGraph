class DummyClass_160438 {
  @Test
  public void stateSelector_shouldReturnEmptyWhenPreForkChoice()
      throws ExecutionException, InterruptedException {
    final StorageQueryChannel historicalChainData = mock(StorageQueryChannel.class);
    final RecentChainData recentChainData = mock(RecentChainData.class);
    final CombinedChainDataClient client1 =
        new CombinedChainDataClient(recentChainData, historicalChainData, spec);
    final StateSelectorFactory factory = new StateSelectorFactory(client1);
    when(recentChainData.isPreGenesis()).thenReturn(false);
    when(recentChainData.isPreForkChoice()).thenReturn(true);
    final SafeFuture<Optional<BeaconState>> future =
        factory.defaultStateSelector(ZERO.toString()).getState();
    assertThat(future.get()).isEmpty();
  }

}