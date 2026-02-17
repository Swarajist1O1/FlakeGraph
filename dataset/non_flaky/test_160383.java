class DummyClass_160383 {
  @Test
  public void getGenesisTime_shouldThrowIfStoreNotAvailable() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, null, mockCombinedChainDataClient);
    when(mockCombinedChainDataClient.isStoreAvailable()).thenReturn(false);
    assertThatThrownBy(provider::getGenesisTime).isInstanceOf(ChainDataUnavailableException.class);
  }

}