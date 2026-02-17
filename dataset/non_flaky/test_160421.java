class DummyClass_160421 {
  @Test
  public void blockRootSelector_shouldGetBlockByBlockRoot()
      throws ExecutionException, InterruptedException {
    when(client.getBlockByBlockRoot(any()))
        .thenReturn(SafeFuture.completedFuture(Optional.of(block)));
    List<SignedBeaconBlock> blockList =
        blockSelectorFactory.forBlockRoot(block.getRoot()).getBlock().get();
    verify(client).getBlockByBlockRoot(block.getRoot());
    assertThat(blockList).containsExactly(block);
  }

}