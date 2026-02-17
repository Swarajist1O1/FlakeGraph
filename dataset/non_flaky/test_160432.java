class DummyClass_160432 {
  @Test
  public void justifiedSelector_shouldGetJustifiedState()
      throws ExecutionException, InterruptedException {
    when(client.getJustifiedState()).thenReturn(SafeFuture.completedFuture(Optional.of(state)));
    Optional<BeaconState> result = factory.justifiedSelector().getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getJustifiedState();
  }

}