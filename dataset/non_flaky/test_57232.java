class DummyClass_57232 {
  @Test
  public void testOverReplicatedRecordRetainedAndUpdated() {
    // under replicated container
    Set<ContainerReplica> replicas =
        generateReplicas(container, CLOSED, CLOSED, CLOSED, CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);

    UnhealthyContainersRecord rec = overReplicatedRecord();
    assertTrue(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
    // The record actual count should be updated from 5 -> 4
    assertEquals(4, rec.getActualReplicaCount().intValue());
    assertEquals(-1, rec.getReplicaDelta().intValue());

    // Missing / Over / Mis replicated should not be retained
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, missingRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, underReplicatedRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, misReplicatedRecord()));

    // Container is now replicated OK - should be removed.
    replicas = generateReplicas(container, CLOSED, CLOSED, CLOSED);
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, rec));
  }

}