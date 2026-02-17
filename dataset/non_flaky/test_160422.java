class DummyClass_160422 {
  @Test
  public void slotSelector_shouldGetBlockAtSlotExact()
      throws ExecutionException, InterruptedException {
    when(client.getBlockAtSlotExact(block.getSlot()))
        .thenReturn(SafeFuture.completedFuture(Optional.of(block)));
    List<SignedBeaconBlock> blockList =
        blockSelectorFactory.forSlot(block.getSlot()).getBlock().get();
    verify(client).getBlockAtSlotExact(block.getSlot());
    assertThat(blockList).containsExactly(block);
  }

}