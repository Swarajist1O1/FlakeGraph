class DummyClass_160414 {
  @Test
  public void getStateFork_shouldGetForkAtGenesis()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    final Bytes4 bytes4 = Bytes4.fromHexString("0x00000001");
    final Optional<Fork> response = provider.getStateFork("genesis").get();
    assertThat(response).isPresent();
    assertThat(response.get()).isEqualTo(new Fork(bytes4, bytes4, ZERO));
  }

}