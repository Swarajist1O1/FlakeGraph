class DummyClass_160434 {
  @Test
  public void forSlot_shouldGetStateAtSlotExact() throws ExecutionException, InterruptedException {
    when(client.getStateAtSlotExact(state.getSlot()))
        .thenReturn(SafeFuture.completedFuture(Optional.of(state)));
    Optional<BeaconState> result = factory.forSlot(state.getSlot()).getState().get();
    assertThat(result).isEqualTo(Optional.of(state));
    verify(client).getStateAtSlotExact(state.getSlot());
  }

}