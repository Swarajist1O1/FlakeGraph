class DummyClass_160430 {
  @Test
  public void headSelector_shouldGetBestState() throws ExecutionException, InterruptedException {
    when(client.getBestState()).thenReturn(Optional.of(state));
    Optional<BeaconState> result = factory.headSelector().getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getBestState();
  }

}