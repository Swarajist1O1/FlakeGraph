class DummyClass_160440 {
  @TestTemplate
  public void validatorsResponseShouldConformToDefaults(SpecContext ctx) {
    BeaconState beaconState = ctx.getDataStructureUtil().randomBeaconState();
    SszList<Validator> validatorList = beaconState.getValidators();
    BeaconValidators response = new BeaconValidators(beaconState, FAR_FUTURE_EPOCH);
    assertThat(response.total_size).isEqualTo(beaconState.getValidators().size());
    assertThat(response.validators.size())
        .isEqualTo(Math.min(validatorList.size(), PAGE_SIZE_DEFAULT));
    int expectedNextPageToken =
        validatorList.size() < PAGE_SIZE_DEFAULT ? 0 : PAGE_TOKEN_DEFAULT + 1;
    assertThat(response.next_page_token).isEqualTo(expectedNextPageToken);
    assertThat(response.validators.get(0).validator.activation_eligibility_epoch)
        .isEqualToComparingFieldByField(validatorList.get(0).getActivation_eligibility_epoch());
    assertThat(response.validators.get(0).validator_index).isEqualTo(0);
  }

}