class DummyClass_57235 {
  @Test
  public void testRecordNotGeneratedIfAlreadyExists() {
    Set<String> existingRec = new HashSet<>();
    for (UnHealthyContainerStates s : UnHealthyContainerStates.values()) {
      existingRec.add(s.toString());
    }

    // Over-replicated
    Set<ContainerReplica> replicas = generateReplicas(
        container, CLOSED, CLOSED, CLOSED, CLOSED, CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    List<UnhealthyContainers> records =
        ContainerHealthTask.ContainerHealthRecords
            .generateUnhealthyRecords(status, existingRec, (long)1234567);
    assertEquals(0, records.size());

    // Missing
    replicas.clear();
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    records = ContainerHealthTask.ContainerHealthRecords
        .generateUnhealthyRecords(status, existingRec, (long)1234567);
    assertEquals(0, records.size());

    // Under and Mis-Replicated
    replicas = generateReplicas(container, CLOSED, CLOSED);
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(1, 2, 5));
    status = new ContainerHealthStatus(container, replicas, placementPolicy);
    records = ContainerHealthTask.ContainerHealthRecords
        .generateUnhealthyRecords(status, existingRec, (long)1234567);
    assertEquals(0, records.size());
  }

}