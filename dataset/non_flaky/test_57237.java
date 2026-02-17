class DummyClass_57237 {
  @Test
  public void testHealthyContainerWithExtraUnhealthyReplica() {
    Set<ContainerReplica> replicas = generateReplicas(container,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.CLOSED,
        ContainerReplicaProto.State.UNHEALTHY);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertTrue(status.isHealthy());
    assertFalse(status.isOverReplicated());
    assertFalse(status.isUnderReplicated());
    assertEquals(0, status.replicaDelta());
    assertFalse(status.isMissing());
    assertEquals(false, status.isMisReplicated());
    assertEquals(0, status.misReplicatedDelta());
  }

}