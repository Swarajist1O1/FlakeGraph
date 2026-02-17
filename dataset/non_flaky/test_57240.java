class DummyClass_57240 {
  @Test
  public void testOverReplicatedContainer() {
    Set<ContainerReplica> replicas = generateReplicas(container,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(status.isHealthy());
    assertFalse(status.isMissing());
    assertFalse(status.isUnderReplicated());
    assertTrue(status.isOverReplicated());
    assertEquals(-1, status.replicaDelta());
    assertEquals(false, status.isMisReplicated());
    assertEquals(0, status.misReplicatedDelta());
  }

}