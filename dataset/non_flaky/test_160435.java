class DummyClass_160435 {
  @Test
  public void forStateRoot_shouldGetStateAtSlotExact()
      throws ExecutionException, InterruptedException {
    when(client.getStateByStateRoot(state.hashTreeRoot()))
        .thenReturn(SafeFuture.completedFuture(Optional.of(state)));
    Optional<BeaconState> result = factory.forStateRoot(state.hashTreeRoot()).getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getStateByStateRoot(state.hashTreeRoot());
  }

}