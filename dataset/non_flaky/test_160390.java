class DummyClass_160390 {
  @Test
  public void validatorParameterToIndex_shouldAcceptValidatorRoot() {
    final ChainDataProvider provider =
        new ChainDataProvider(spec, recentChainData, combinedChainDataClient);

    Validator validator =
        new Validator(recentChainData.getBestState().get().getValidators().get(1));

    assertThat(provider.validatorParameterToIndex(validator.pubkey.toHexString()))
        .isEqualTo(Optional.of(1));
  }

}