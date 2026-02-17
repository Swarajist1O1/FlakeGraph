class DummyClass_160386 {
  @Test
  public void getGenesisData_shouldReturnValueIfStoreAvailable() {
    final UInt64 genesisTime = beaconStateInternal.getGenesis_time();
    final Bytes32 genesisValidatorsRoot = beaconStateInternal.getGenesis_validators_root();
    final Bytes4 genesisForkVersion = spec.atEpoch(ZERO).getConfig().getGenesisForkVersion();

    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    final GenesisData result = provider.getGenesisData();
    assertThat(result)
        .isEqualTo(new GenesisData(genesisTime, genesisValidatorsRoot, genesisForkVersion));
  }

}