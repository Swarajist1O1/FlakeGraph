class DummyClass_160411 {
  @Test
  public void getStateSyncCommittees_shouldGetCommittees()
      throws ExecutionException, InterruptedException {
    final ChainDataProvider provider = setupAltairState();
    final List<UInt64> committeeIndices =
        List.of(UInt64.valueOf(6), UInt64.valueOf(9), UInt64.valueOf(0));

    final SafeFuture<Optional<StateSyncCommittees>> future =
        provider.getStateSyncCommittees("head", Optional.empty());
    assertThat(future).isCompleted();
    assertThat(future.get().get())
        .isEqualTo(new StateSyncCommittees(committeeIndices, List.of(committeeIndices)));
  }

}