class DummyClass_160428 {
  @TestTemplate
  public void getAttesterDuties_shouldHandleEmptyIndexesList() {
    final Bytes32 previousTargetRoot = dataStructureUtil.randomBytes32();
    when(validatorApiChannel.getAttestationDuties(eq(ONE), any()))
        .thenReturn(
            completedFuture(
                Optional.of(
                    new tech.pegasys.teku.validator.api.AttesterDuties(
                        previousTargetRoot, emptyList()))));
    final SafeFuture<Optional<PostAttesterDutiesResponse>> future =
        provider.getAttesterDuties(UInt64.ONE, List.of());
    assertThat(future).isCompleted();
    Optional<PostAttesterDutiesResponse> maybeData = future.join();
    assertThat(maybeData.isPresent()).isTrue();
    assertThat(maybeData.get().data).isEmpty();
  }

}