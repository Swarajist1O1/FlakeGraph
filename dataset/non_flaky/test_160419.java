class DummyClass_160419 {
  @Test
  public void finalizedSelector_shouldGetFinalizedBlock()
      throws ExecutionException, InterruptedException {
    when(client.getFinalizedBlock()).thenReturn(Optional.of(block));
    List<SignedBeaconBlock> blockList = blockSelectorFactory.finalizedSelector().getBlock().get();
    verify(client).getFinalizedBlock();
    assertThat(blockList).containsExactly(block);
  }

}