class DummyClass_160393 {
  @Test
  public void validatorParameterToIndex_shouldDetectAboveMaxInt() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThrows(
        BadRequestException.class,
        () ->
            provider.validatorParameterToIndex(
                UInt64.valueOf(Integer.MAX_VALUE).increment().toString()));
  }

}