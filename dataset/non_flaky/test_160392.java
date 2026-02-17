class DummyClass_160392 {
  @Test
  public void validatorParameterToIndex_shouldThrowException() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThrows(BadRequestException.class, () -> provider.validatorParameterToIndex("2a"));
  }

}