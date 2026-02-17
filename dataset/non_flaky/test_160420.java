class DummyClass_160420 {
  @Test
  public void genesisSelector_shouldGetSlotZero() throws ExecutionException, InterruptedException {
    when(client.getBlockAtSlotExact(UInt64.ZERO))
        .thenReturn(SafeFuture.completedFuture(Optional.of(block)));
    List<SignedBeaconBlock> blockList = blockSelectorFactory.genesisSelector().getBlock().get();
    verify(client).getBlockAtSlotExact(UInt64.ZERO);
    assertThat(blockList).containsExactly(block);
  }

}