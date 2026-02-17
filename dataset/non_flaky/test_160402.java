class DummyClass_160402 {
  @Test
  public void validatorParameterToIndex_shouldThrowBadRequestExceptionWhenIndexInvalid() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    assertThrows(BadRequestException.class, () -> provider.validatorParameterToIndex("a"));
  }

}