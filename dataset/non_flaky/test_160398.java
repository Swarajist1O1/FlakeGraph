class DummyClass_160398 {
  @Test
  public void getBlockHeaders_shouldGetBlockGivenSlot()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final UInt64 slot = combinedChainDataClient.getCurrentSlot();
    List<BlockHeader> results = provider.getBlockHeaders(Optional.empty(), Optional.of(slot)).get();
    assertThat(results.get(0).header.message.slot).isEqualTo(slot);
  }

}