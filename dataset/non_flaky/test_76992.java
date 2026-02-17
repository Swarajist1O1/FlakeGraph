class DummyClass_76992 {
  @Test
  public void testGetPartitionToServers() {
    GetShuffleAssignmentsResponse testResponse = generateShuffleAssignmentsResponse();

    Map<Integer, List<ShuffleServerInfo>> partitionToServers =
        coordinatorClient.getPartitionToServers(testResponse);

    assertEquals(Arrays.asList(new ShuffleServerInfo("id1", "0.0.0.1", 100),
        new ShuffleServerInfo("id2", "0.0.0.2", 100)),
        partitionToServers.get(0));
    assertEquals(Arrays.asList(new ShuffleServerInfo("id1", "0.0.0.1", 100),
        new ShuffleServerInfo("id2", "0.0.0.2", 100)),
        partitionToServers.get(1));
    assertEquals(Arrays.asList(new ShuffleServerInfo("id3", "0.0.0.3", 100),
        new ShuffleServerInfo("id4", "0.0.0.4", 100)),
        partitionToServers.get(2));
    assertEquals(Arrays.asList(new ShuffleServerInfo("id3", "0.0.0.3", 100),
        new ShuffleServerInfo("id4", "0.0.0.4", 100)),
        partitionToServers.get(3));
    assertNull(partitionToServers.get(4));
  }

}