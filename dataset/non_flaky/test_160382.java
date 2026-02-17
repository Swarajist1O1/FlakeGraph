class DummyClass_160382 {
  @Test
  public void getChainHeads_shouldReturnChainHeads()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final SafeFuture<Optional<List<ChainHead>>> future = provider.getChainHeads();
    final Optional<List<ChainHead>> maybeResult = future.get();
    assertThat(maybeResult.orElse(emptyList()))
        .containsExactly(new ChainHead(bestBlock.getSlot(), blockRoot));
  }

}