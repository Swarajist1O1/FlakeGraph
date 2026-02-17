class DummyClass_160403 {
  @Test
  public void validatorParameterToIndex_shouldReturnEmptyIfIndexOutOfBounds() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    assertThat(provider.validatorParameterToIndex("1024000")).isEmpty();
  }

}