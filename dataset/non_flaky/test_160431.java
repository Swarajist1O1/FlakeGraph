class DummyClass_160431 {
  @Test
  public void finalizedSelector_shouldGetFinalizedState()
      throws ExecutionException, InterruptedException {
    when(client.getFinalizedState()).thenReturn(Optional.of(state));
    Optional<BeaconState> result = factory.finalizedSelector().getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getFinalizedState();
  }

}