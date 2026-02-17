class DummyClass_160389 {
  @Test
  public void validatorParameterToIndex_shouldThrowWhenStoreNotFound() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, null, mockCombinedChainDataClient);
    assertThrows(
        ChainDataUnavailableException.class, () -> provider.validatorParameterToIndex("1"));
  }

}