class DummyClass_57238 {
  @Test
  public void testMissingContainer() {
    Set<ContainerReplica> replicas = new HashSet<>();
    ContainerHealthStatus status =
        new ContainerHealthStatus(container, replicas, placementPolicy);
    assertFalse(status.isHealthy());
    assertFalse(status.isOverReplicated());
    assertFalse(status.isUnderReplicated());
    assertEquals(3, status.replicaDelta());
    assertTrue(status.isMissing());
    assertEquals(false, status.isMisReplicated());
    assertEquals(0, status.misReplicatedDelta());
  }

}