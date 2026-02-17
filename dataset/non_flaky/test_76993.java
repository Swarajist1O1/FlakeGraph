class DummyClass_76993 {
  @Test
  public void getShuffleRegisterInfoTest() {
    GetShuffleAssignmentsResponse testResponse = generateShuffleAssignmentsResponse();
    Map<ShuffleServerInfo, List<PartitionRange>> serverToPartitionRanges =
        coordinatorClient.getServerToPartitionRanges(testResponse);
    List<ShuffleRegisterInfo> expected = Arrays.asList(
        new ShuffleRegisterInfo(new ShuffleServerInfo("id1", "0.0.0.1", 100),
            Lists.newArrayList(new PartitionRange(0, 1))),
        new ShuffleRegisterInfo(new ShuffleServerInfo("id2", "0.0.0.2", 100),
            Lists.newArrayList(new PartitionRange(0, 1))),
        new ShuffleRegisterInfo(new ShuffleServerInfo("id3", "0.0.0.3", 100),
            Lists.newArrayList(new PartitionRange(2, 3))),
        new ShuffleRegisterInfo(new ShuffleServerInfo("id4", "0.0.0.4", 100),
            Lists.newArrayList(new PartitionRange(2, 3))));
    assertEquals(4, serverToPartitionRanges.size());
    for (ShuffleRegisterInfo sri : expected) {
      List<PartitionRange> partitionRanges = serverToPartitionRanges.get(sri.getShuffleServerInfo());
      assertEquals(sri.getPartitionRanges(), partitionRanges);
    }
  }

}