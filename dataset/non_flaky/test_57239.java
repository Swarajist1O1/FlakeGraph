class DummyClass_57239 {
  @Test
  public void testUnderReplicatedContainer() {
    Set<ContainerReplica> replicas = generateReplicas(container,
        ContainerReplicaProto.State.CLOSED);
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(status.isHealthy());
    assertFalse(status.isMissing());
    assertFalse(status.isOverReplicated());
    assertTrue(status.isUnderReplicated());
    assertEquals(2, status.replicaDelta());
    assertEquals(false, status.isMisReplicated());
    assertEquals(0, status.misReplicatedDelta());
  }

}