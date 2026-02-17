class DummyClass_76994 {
  @Test
  public void getShuffleAssignmentsTest() throws Exception {
    String appId = "getShuffleAssignmentsTest";
    CoordinatorTestUtils.waitForRegister(coordinatorClient,2);
    RssGetShuffleAssignmentsRequest request = new RssGetShuffleAssignmentsRequest(
        appId, 1, 10, 4, 1,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    RssGetShuffleAssignmentsResponse response = coordinatorClient.getShuffleAssignments(request);
    Set<Integer> expectedStart = Sets.newHashSet(0, 4, 8);

    Map<ShuffleServerInfo, List<PartitionRange>> serverToPartitionRanges = response.getServerToPartitionRanges();
    assertEquals(2, serverToPartitionRanges.size());
    List<PartitionRange> partitionRanges = Lists.newArrayList();
    for (List<PartitionRange> ranges : serverToPartitionRanges.values()) {
      partitionRanges.addAll(ranges);
    }
    for (PartitionRange pr : partitionRanges) {
      switch (pr.getStart()) {
        case 0:
          assertEquals(3, pr.getEnd());
          expectedStart.remove(0);
          break;
        case 4:
          assertEquals(7, pr.getEnd());
          expectedStart.remove(4);
          break;
        case 8:
          assertEquals(11, pr.getEnd());
          expectedStart.remove(8);
          break;
        default:
          fail("Shouldn't be here");
      }
    }
    assertTrue(expectedStart.isEmpty());

    request = new RssGetShuffleAssignmentsRequest(
        appId, 1, 10, 4, 2,
        Sets.newHashSet(Constants.SHUFFLE_SERVER_VERSION));
    response = coordinatorClient.getShuffleAssignments(request);
    serverToPartitionRanges = response.getServerToPartitionRanges();
    assertEquals(2, serverToPartitionRanges.size());
    partitionRanges = Lists.newArrayList();
    for (List<PartitionRange> ranges : serverToPartitionRanges.values()) {
      partitionRanges.addAll(ranges);
    }
    assertEquals(6, partitionRanges.size());
    int range0To3 = 0;
    int range4To7 = 0;
    int range8To11 = 0;
    for (PartitionRange pr : partitionRanges) {
      switch (pr.getStart()) {
        case 0:
          assertEquals(3, pr.getEnd());
          range0To3++;
          break;
        case 4:
          assertEquals(7, pr.getEnd());
          range4To7++;
          break;
        case 8:
          assertEquals(11, pr.getEnd());
          range8To11++;
          break;
        default:
          fail("Shouldn't be here");
      }
    }
    assertEquals(2, range0To3);
    assertEquals(2, range4To7);
    assertEquals(2, range8To11);

    request = new RssGetShuffleAssignmentsRequest(
        appId, 3, 2, 1, 1,
        Sets.newHashSet("fake_version"));
    try {
      coordinatorClient.getShuffleAssignments(request);
      fail("Exception should be thrown");
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Empty assignment"));
    }
  }

}