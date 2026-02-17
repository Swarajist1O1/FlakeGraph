class DummyClass_57233 {
  @Test
  public void testMisReplicatedRecordRetainedAndUpdated() {
    // under replicated container
    Set<ContainerReplica> replicas =
        generateReplicas(container, CLOSED, CLOSED, CLOSED);
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(2, 3, 5));
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);

    UnhealthyContainersRecord rec = misReplicatedRecord();
    assertTrue(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
    // The record actual count should be updated from 1 -> 2
    assertEquals(2, rec.getActualReplicaCount().intValue());
    assertEquals(1, rec.getReplicaDelta().intValue());
    assertNotNull(rec.getReason());

    // Missing / Over / Mis replicated should not be retained
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, missingRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, underReplicatedRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, overReplicatedRecord()));

    // Container is now placed OK - should be removed.
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(3, 3, 5));
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
  }

}