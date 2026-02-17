class DummyClass_160408 {
  @Test
  public void getCommitteesFromState_shouldFilterOnSlot() {
    final tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState internalState =
        data.randomBeaconState(64);
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThat(
            provider
                .getCommitteesFromState(
                    internalState,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(internalState.getSlot()))
                .size())
        .isEqualTo(1);
  }

}