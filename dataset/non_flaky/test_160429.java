class DummyClass_160429 {
  @TestTemplate
  public void getAttesterDuties_shouldReturnDutiesForKnownValidator() {
    AttesterDuty v1 = new AttesterDuty(BLSTestUtil.randomPublicKey(0), 1, 2, 3, 15, 4, ONE);
    AttesterDuty v2 = new AttesterDuty(BLSTestUtil.randomPublicKey(1), 11, 12, 13, 15, 14, ZERO);
    when(validatorApiChannel.getAttestationDuties(eq(ONE), any()))
        .thenReturn(
            completedFuture(
                Optional.of(
                    new AttesterDuties(dataStructureUtil.randomBytes32(), List.of(v1, v2)))));

    final SafeFuture<Optional<PostAttesterDutiesResponse>> future =
        provider.getAttesterDuties(ONE, List.of(1, 11));
    assertThat(future).isCompleted();
    final Optional<PostAttesterDutiesResponse> maybeList = future.join();
    final PostAttesterDutiesResponse list = maybeList.orElseThrow();
    assertThat(list.data).containsExactlyInAnyOrder(asAttesterDuty(v1), asAttesterDuty(v2));
  }

}