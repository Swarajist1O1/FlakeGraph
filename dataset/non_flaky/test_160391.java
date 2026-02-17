class DummyClass_160391 {
  @Test
  public void validatorParameterToIndex_shouldAcceptValidatorId() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    assertThat(provider.validatorParameterToIndex("2")).isEqualTo(Optional.of(2));
  }

}