class DummyClass_57255 {
  @Test
  public void testUpdateAndRemoveContainerReplica() throws IOException {
    // Sanity checking updateContainerReplica and ContainerReplicaHistory

    // Init Container 1
    final long cIDlong1 = 1L;
    final ContainerID containerID1 = ContainerID.valueOf(cIDlong1);

    // Init DN01
    final UUID uuid1 = UUID.randomUUID();
    final DatanodeDetails datanodeDetails1 = DatanodeDetails.newBuilder()
        .setUuid(uuid1).setHostName("host1").setIpAddress("127.0.0.1").build();
    ContainerReplica containerReplica1 = ContainerReplica.newBuilder()
        .setContainerID(containerID1).setContainerState(State.OPEN)
        .setDatanodeDetails(datanodeDetails1).setSequenceId(1001L).build();

    final ReconContainerManager containerManager = getContainerManager();
    final Map<Long, Map<UUID, ContainerReplicaHistory>> repHistMap =
        containerManager.getReplicaHistoryMap();
    // Should be empty at the beginning
    Assert.assertEquals(0, repHistMap.size());

    // Put a replica info and call updateContainerReplica
    Pipeline pipeline = getRandomPipeline();
    getPipelineManager().addPipeline(pipeline);
    for (int i = 1; i <= 10; i++) {
      final ContainerInfo info = newContainerInfo(i, pipeline);
      containerManager.addNewContainer(
          new ContainerWithPipeline(info, pipeline));
    }

    containerManager.updateContainerReplica(containerID1, containerReplica1);
    // Should have 1 container entry in the replica history map
    Assert.assertEquals(1, repHistMap.size());
    // Should only have 1 entry for this replica (on DN01)
    Assert.assertEquals(1, repHistMap.get(cIDlong1).size());
    ContainerReplicaHistory repHist1 = repHistMap.get(cIDlong1).get(uuid1);
    Assert.assertEquals(uuid1, repHist1.getUuid());
    // Because this is a new entry, first seen time equals last seen time
    assertEquals(repHist1.getLastSeenTime(), repHist1.getFirstSeenTime());
    assertEquals(containerReplica1.getSequenceId().longValue(),
        repHist1.getBcsId());

    // Let's update the entry again
    containerReplica1 = ContainerReplica.newBuilder()
        .setContainerID(containerID1).setContainerState(State.OPEN)
        .setDatanodeDetails(datanodeDetails1).setSequenceId(1051L).build();
    containerManager.updateContainerReplica(containerID1, containerReplica1);
    // Should still have 1 entry in the replica history map
    Assert.assertEquals(1, repHistMap.size());
    // Now last seen time should be larger than first seen time
    Assert.assertTrue(repHist1.getLastSeenTime() > repHist1.getFirstSeenTime());
    assertEquals(1051L, repHist1.getBcsId());

    // Init DN02
    final UUID uuid2 = UUID.randomUUID();
    final DatanodeDetails datanodeDetails2 = DatanodeDetails.newBuilder()
        .setUuid(uuid2).setHostName("host2").setIpAddress("127.0.0.2").build();
    final ContainerReplica containerReplica2 = ContainerReplica.newBuilder()
        .setContainerID(containerID1).setContainerState(State.OPEN)
        .setDatanodeDetails(datanodeDetails2).setSequenceId(1051L).build();

    // Add replica to DN02
    containerManager.updateContainerReplica(containerID1, containerReplica2);

    // Should still have 1 container entry in the replica history map
    Assert.assertEquals(1, repHistMap.size());
    // Should have 2 entries for this replica (on DN01 and DN02)
    Assert.assertEquals(2, repHistMap.get(cIDlong1).size());
    ContainerReplicaHistory repHist2 = repHistMap.get(cIDlong1).get(uuid2);
    Assert.assertEquals(uuid2, repHist2.getUuid());
    // Because this is a new entry, first seen time equals last seen time
    assertEquals(repHist2.getLastSeenTime(), repHist2.getFirstSeenTime());
    assertEquals(1051L, repHist2.getBcsId());

    // Remove replica from DN01
    containerManager.removeContainerReplica(containerID1, containerReplica1);
    // Should still have 1 container entry in the replica history map
    Assert.assertEquals(1, repHistMap.size());
    // Should have 1 entry for this replica
    Assert.assertEquals(1, repHistMap.get(cIDlong1).size());
    // And the only entry should match DN02
    Assert.assertEquals(uuid2,
        repHistMap.get(cIDlong1).keySet().iterator().next());
  }

}