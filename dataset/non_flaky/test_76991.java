class DummyClass_76991 {
  @Test
  public void emptyTaskTest() {
    String testAppId = "emptyTaskTest";
    shuffleWriteClientImpl.registerShuffle(shuffleServerInfo1,
        testAppId, 0, Lists.newArrayList(new PartitionRange(0, 0)));
    boolean commitResult = shuffleWriteClientImpl
        .sendCommit(Sets.newHashSet(shuffleServerInfo1), testAppId, 0, 2);
    assertTrue(commitResult);
    commitResult = shuffleWriteClientImpl
        .sendCommit(Sets.newHashSet(shuffleServerInfo2), testAppId, 0, 2);
    assertFalse(commitResult);
  }

}