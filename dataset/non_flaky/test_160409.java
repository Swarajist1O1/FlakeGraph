class DummyClass_160409 {
  @Test
  public void getStateFinalityCheckpoints_shouldGetEmptyCheckpointsBeforeFinalized()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThat(provider.getStateFinalityCheckpoints("genesis").get().get())
        .isEqualTo(
            new FinalityCheckpointsResponse(
                tech.pegasys.teku.api.schema.Checkpoint.EMPTY,
                tech.pegasys.teku.api.schema.Checkpoint.EMPTY,
                tech.pegasys.teku.api.schema.Checkpoint.EMPTY));
  }

}