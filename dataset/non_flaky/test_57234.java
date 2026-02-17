class DummyClass_57234 {
  @Test
  public void testCorrectRecordsGenerated() {
    Set<ContainerReplica> replicas =
        generateReplicas(container, CLOSED, CLOSED, CLOSED);

    // HEALTHY container - no records generated.
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    List<UnhealthyContainers> records =
        ContainerHealthTask.ContainerHealthRecords
            .generateUnhealthyRecords(status, (long)1234567);
    assertEquals(0, records.size());

    // Over-replicated - expect 1 over replicated record
    replicas =
        generateReplicas(container, CLOSED, CLOSED, CLOSED, CLOSED, CLOSED);
    status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    records = ContainerHealthTask.ContainerHealthRecords
        .generateUnhealthyRecords(status, (long)1234567);
    assertEquals(1, records.size());
    UnhealthyContainers rec = records.get(0);
    assertEquals(UnHealthyContainerStates.OVER_REPLICATED.toString(),
        rec.getContainerState());
    assertEquals(3, rec.getExpectedReplicaCount().intValue());
    assertEquals(5, rec.getActualReplicaCount().intValue());
    assertEquals(-2, rec.getReplicaDelta().intValue());

    // Under and Mis Replicated - expect 2 records - mis and under replicated
    replicas =
        generateReplicas(container, CLOSED, CLOSED);
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(1, 2, 5));
    status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    records = ContainerHealthTask.ContainerHealthRecords
        .generateUnhealthyRecords(status, (long)1234567);
    assertEquals(2, records.size());

    rec = findRecordForState(records, UnHealthyContainerStates.MIS_REPLICATED);
    assertEquals(UnHealthyContainerStates.MIS_REPLICATED.toString(),
        rec.getContainerState());
    assertEquals(2, rec.getExpectedReplicaCount().intValue());
    assertEquals(1, rec.getActualReplicaCount().intValue());
    assertEquals(1, rec.getReplicaDelta().intValue());
    assertNotNull(rec.getReason());

    rec = findRecordForState(records,
        UnHealthyContainerStates.UNDER_REPLICATED);
    assertEquals(UnHealthyContainerStates.UNDER_REPLICATED.toString(),
        rec.getContainerState());
    assertEquals(3, rec.getExpectedReplicaCount().intValue());
    assertEquals(2, rec.getActualReplicaCount().intValue());
    assertEquals(1, rec.getReplicaDelta().intValue());

    // Missing Record - expect just a single missing record even though
    // it is mis-replicated too
    replicas.clear();
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(1, 2, 5));
    status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    records = ContainerHealthTask.ContainerHealthRecords
        .generateUnhealthyRecords(status, (long)1234567);
    assertEquals(1, records.size());
    rec = records.get(0);
    assertEquals(UnHealthyContainerStates.MISSING.toString(),
        rec.getContainerState());
    assertEquals(3, rec.getExpectedReplicaCount().intValue());
    assertEquals(0, rec.getActualReplicaCount().intValue());
    assertEquals(3, rec.getReplicaDelta().intValue());
  }

}