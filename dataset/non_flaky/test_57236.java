class DummyClass_57236 {
  @Test
  public void testHealthyContainer() {
    Set<ContainerReplica> replicas = generateReplicas(container,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertTrue(status.isHealthy());
    assertFalse(status.isOverReplicated());
    assertFalse(status.isUnderReplicated());
    assertEquals(0, status.replicaDelta());
    assertFalse(status.isMissing());
    assertEquals(false, status.isMisReplicated());
    assertEquals(0, status.misReplicatedDelta());

    assertEquals(container, status.getContainer());
    assertEquals((long)123456, status.getContainerID());
    assertEquals(3, status.getReplicationFactor());
    assertEquals(3, status.getReplicaCount());
  }

}