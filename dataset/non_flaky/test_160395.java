class DummyClass_160395 {
  @Test
  public void getBlockHeaderByBlockId_shouldGetHeadBlock()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final tech.pegasys.teku.spec.datastructures.blocks.SignedBeaconBlock block =
        combinedChainDataClient.getBestBlock().get();
    BlockHeader result = provider.getBlockHeader("head").get().get();
    final BeaconBlockHeader beaconBlockHeader =
        new BeaconBlockHeader(
            block.getSlot(),
            block.getMessage().getProposerIndex(),
            block.getParentRoot(),
            block.getStateRoot(),
            block.getRoot());
    final BlockHeader expected =
        new BlockHeader(
            block.getRoot(),
            true,
            new SignedBeaconBlockHeader(beaconBlockHeader, new BLSSignature(block.getSignature())));

    assertThat(result).isEqualTo(expected);
  }

}