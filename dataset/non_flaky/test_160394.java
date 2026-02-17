class DummyClass_160394 {
  @Test
  public void validatorParameterToIndex_shouldThrowExceptionWithInvalidPublicKey() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThrows(
        BadRequestException.class,
        () -> provider.validatorParameterToIndex(Bytes32.EMPTY.toHexString()));
  }

}