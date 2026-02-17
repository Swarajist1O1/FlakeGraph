class DummyClass_160381 {
  @BeforeEach
  public void setup() {
    slot = UInt64.valueOf(specConfig.getSlotsPerEpoch() * 3L);
    actualBalance = specConfig.getMaxEffectiveBalance().plus(100000);
    storageSystem.chainUpdater().initializeGenesis(true, actualBalance, Optional.empty());
    bestBlock = storageSystem.chainUpdater().advanceChain(slot);
    storageSystem.chainUpdater().updateBestBlock(bestBlock);

    recentChainData = storageSystem.recentChainData();
    beaconStateInternal = bestBlock.getState();

    combinedChainDataClient = storageSystem.combinedChainDataClient();
    blockRoot = bestBlock.getRoot();
  }

}