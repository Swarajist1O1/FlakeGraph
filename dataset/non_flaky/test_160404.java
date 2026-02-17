class DummyClass_160404 {
  @Test
  public void validatorParameterToIndex_shouldThrowBadRequestExceptionWhenKeyNotFound() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);
    assertThrows(
        BadRequestException.class,
        () -> provider.validatorParameterToIndex(Bytes32.fromHexString("0x00").toHexString()));
  }

}