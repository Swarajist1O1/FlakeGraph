class DummyClass_160413 {
  @Test
  public void getStateSyncCommittees_shouldRejectFarFutureEpoch() {
    final ChainDataProvider provider = setupAltairState();
    final SafeFuture<Optional<StateSyncCommittees>> future =
        provider.getStateSyncCommittees("head", Optional.of(UInt64.valueOf("1024000")));
    SafeFutureAssert.assertThatSafeFuture(future)
        .isCompletedExceptionallyWith(IllegalArgumentException.class);
  }

}