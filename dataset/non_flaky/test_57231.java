class DummyClass_57231 {
  @Test
  public void testUnderReplicatedRecordRetainedAndUpdated() {
    // under replicated container
    Set<ContainerReplica> replicas =
        generateReplicas(container, CLOSED, CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);

    UnhealthyContainersRecord rec = underReplicatedRecord();
    assertTrue(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
    // The record actual count should be updated from 1 -> 2
    assertEquals(2, rec.getActualReplicaCount().intValue());
    assertEquals(1, rec.getReplicaDelta().intValue());

    // Missing / Over / Mis replicated should not be retained
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, missingRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, overReplicatedRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, misReplicatedRecord()));

    // Container is now replicated OK - should be removed.
    replicas = generateReplicas(container, CLOSED, CLOSED, CLOSED);
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
  }

}