class DummyClass_160416 {
  @Test
  public void getBlockRoot_shouldReturnRootOfBlock() throws Exception {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final Optional<Root> response = provider.getBlockRoot("head").get();
    assertThat(response).isPresent();
    assertThat(response.get()).isEqualTo(new Root(bestBlock.getRoot()));
  }

}