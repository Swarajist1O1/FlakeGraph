class DummyClass_160397 {
  @Test
  public void getBlockHeaders_shouldGetHeadBlockIfNoParameters()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final tech.pegasys.teku.spec.datastructures.blocks.SignedBeaconBlock block =
        combinedChainDataClient.getBestBlock().get();
    List<BlockHeader> results = provider.getBlockHeaders(Optional.empty(), Optional.empty()).get();
    assertThat(results.get(0).root).isEqualTo(block.getRoot());
  }

}