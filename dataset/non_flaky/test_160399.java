class DummyClass_160399 {
  @Test
  public void shouldGetBlockHeadersOnEmptyChainHeadSlot() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    final UInt64 headSlot = recentChainData.getHeadSlot();
    storageSystem.chainUpdater().advanceChain(headSlot.plus(1));

    final SafeFuture<List<BlockHeader>> future =
        provider.getBlockHeaders(Optional.empty(), Optional.empty());
    final BlockHeader header = future.join().get(0);
    assertThat(header.header.message.slot).isEqualTo(headSlot);
  }

}