class DummyClass_57241 {
  @Test
  public void testMisReplicated() {
    Set<ContainerReplica> replicas = generateReplicas(container,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED);
    when(placementPolicy.validateContainerPlacement(
        Mockito.anyList(), Mockito.anyInt()))
        .thenReturn(new ContainerPlacementStatusDefault(1, 2, 5));
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(status.isHealthy());
    assertFalse(status.isMissing());
    assertFalse(status.isUnderReplicated());
    assertFalse(status.isOverReplicated());
    assertEquals(0, status.replicaDelta());
    assertTrue(status.isMisReplicated());
    assertEquals(1, status.misReplicatedDelta());
  }

}