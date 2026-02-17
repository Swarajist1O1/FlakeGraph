class DummyClass_160400 {
  @Test
  public void filteredValidatorsList_shouldFilterByValidatorIndex() {

    final tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState internalState =
        data.randomBeaconState(1024);
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    List<Integer> indexes =
        provider.getFilteredValidatorList(internalState, List.of("1", "33"), emptySet()).stream()
            .map(v -> v.index.intValue())
            .collect(toList());
    assertThat(indexes).containsExactly(1, 33);
  }

}