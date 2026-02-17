class DummyClass_160401 {
  @Test
  public void filteredValidatorsList_shouldFilterByValidatorPubkey() {
    final tech.pegasys.teku.spec.datastructures.state.beaconstate.BeaconState internalState =
        data.randomBeaconState(1024);
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    final String key = internalState.getValidators().get(12).getPubkeyBytes().toString();
    final String missingKey = data.randomPublicKey().toString();
    List<String> pubkeys =
        provider
            .getFilteredValidatorList(internalState, List.of(key, missingKey), emptySet())
            .stream()
            .map(v -> v.validator.pubkey.toHexString())
            .collect(toList());
    assertThat(pubkeys).containsExactly(key);
  }

}