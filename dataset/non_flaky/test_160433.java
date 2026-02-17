class DummyClass_160433 {
  @Test
  public void genesisSelector_shouldGetStateAtSlotExact()
      throws ExecutionException, InterruptedException {
    when(client.getStateAtSlotExact(ZERO))
        .thenReturn(SafeFuture.completedFuture(Optional.of(state)));
    Optional<BeaconState> result = factory.genesisSelector().getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getStateAtSlotExact(ZERO);
  }

}