class DummyClass_57230 {
  @Test
  public void testMissingRecordRetained() {
    Set<ContainerReplica> replicas = new HashSet<>();
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    // Missing record should be retained
    assertTrue(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, missingRecord()));
    // Under / Over / Mis replicated should not be retained as if a container is
    // missing then it is not in any other category.
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, underReplicatedRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, overReplicatedRecord()));
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, misReplicatedRecord()));

    replicas = generateReplicas(container, CLOSED, CLOSED, CLOSED);
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(ContainerHealthTask.ContainerHealthRecords
        .retainOrUpdateRecord(status, missingRecord()));
  }

}