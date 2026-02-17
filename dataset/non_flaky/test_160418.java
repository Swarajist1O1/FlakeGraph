class DummyClass_160418 {
  @Test
  public void headSelector_shouldGetBestBlock() throws ExecutionException, InterruptedException {
    when(client.getBestBlock()).thenReturn(Optional.of(block));
    List<SignedBeaconBlock> blockList = blockSelectorFactory.headSelector().getBlock().get();
    verify(client).getBestBlock();
    assertThat(blockList).containsExactly(block);
  }

}