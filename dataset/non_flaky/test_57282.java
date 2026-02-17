class DummyClass_57282 {
  @Test
  public void testUnhealthyContainers() throws IOException {
    Response response = containerEndpoint.getUnhealthyContainers(1000, 1);

    UnhealthyContainersResponse responseObject =
        (UnhealthyContainersResponse) response.getEntity();

    assertEquals(0, responseObject.getMissingCount());
    assertEquals(0, responseObject.getOverReplicatedCount());
    assertEquals(0, responseObject.getUnderReplicatedCount());
    assertEquals(0, responseObject.getMisReplicatedCount());

    assertEquals(Collections.EMPTY_LIST, responseObject.getContainers());

    putContainerInfos(14);
    uuid1 = newDatanode("host1", "127.0.0.1");
    uuid2 = newDatanode("host2", "127.0.0.2");
    uuid3 = newDatanode("host3", "127.0.0.3");
    uuid4 = newDatanode("host4", "127.0.0.4");
    createUnhealthyRecords(5, 4, 3, 2);

    response = containerEndpoint.getUnhealthyContainers(1000, 1);

    responseObject = (UnhealthyContainersResponse) response.getEntity();
    assertEquals(5, responseObject.getMissingCount());
    assertEquals(4, responseObject.getOverReplicatedCount());
    assertEquals(3, responseObject.getUnderReplicatedCount());
    assertEquals(2, responseObject.getMisReplicatedCount());

    Collection<UnhealthyContainerMetadata> records
        = responseObject.getContainers();
    List<UnhealthyContainerMetadata> missing = records
        .stream()
        .filter(r -> r.getContainerState()
            .equals(UnHealthyContainerStates.MISSING.toString()))
        .collect(Collectors.toList());
    assertEquals(5, missing.size());
    assertEquals(3, missing.get(0).getExpectedReplicaCount());
    assertEquals(0, missing.get(0).getActualReplicaCount());
    assertEquals(3, missing.get(0).getReplicaDeltaCount());
    assertEquals(12345L, missing.get(0).getUnhealthySince());
    assertEquals(1L, missing.get(0).getContainerID());
    assertEquals(keyCount, missing.get(0).getKeys());
    assertEquals(pipelineID.getId(), missing.get(0).getPipelineID());
    assertEquals(3, missing.get(0).getReplicas().size());
    assertNull(missing.get(0).getReason());

    Set<String> datanodes = Collections.unmodifiableSet(
        new HashSet<>(Arrays.asList("host2", "host3", "host4")));
    List<ContainerHistory> containerReplicas = missing.get(0).getReplicas();
    containerReplicas.forEach(history -> {
      Assert.assertTrue(datanodes.contains(history.getDatanodeHost()));
    });

    List<UnhealthyContainerMetadata> overRep = records
        .stream()
        .filter(r -> r.getContainerState()
            .equals(UnHealthyContainerStates.OVER_REPLICATED.toString()))
        .collect(Collectors.toList());
    assertEquals(4, overRep.size());
    assertEquals(3, overRep.get(0).getExpectedReplicaCount());
    assertEquals(5, overRep.get(0).getActualReplicaCount());
    assertEquals(-2, overRep.get(0).getReplicaDeltaCount());
    assertEquals(12345L, overRep.get(0).getUnhealthySince());
    assertEquals(6L, overRep.get(0).getContainerID());
    assertNull(overRep.get(0).getReason());

    List<UnhealthyContainerMetadata> underRep = records
        .stream()
        .filter(r -> r.getContainerState()
            .equals(UnHealthyContainerStates.UNDER_REPLICATED.toString()))
        .collect(Collectors.toList());
    assertEquals(3, underRep.size());
    assertEquals(3, underRep.get(0).getExpectedReplicaCount());
    assertEquals(1, underRep.get(0).getActualReplicaCount());
    assertEquals(2, underRep.get(0).getReplicaDeltaCount());
    assertEquals(12345L, underRep.get(0).getUnhealthySince());
    assertEquals(10L, underRep.get(0).getContainerID());
    assertNull(underRep.get(0).getReason());

    List<UnhealthyContainerMetadata> misRep = records
        .stream()
        .filter(r -> r.getContainerState()
            .equals(UnHealthyContainerStates.MIS_REPLICATED.toString()))
        .collect(Collectors.toList());
    assertEquals(2, misRep.size());
    assertEquals(2, misRep.get(0).getExpectedReplicaCount());
    assertEquals(1, misRep.get(0).getActualReplicaCount());
    assertEquals(1, misRep.get(0).getReplicaDeltaCount());
    assertEquals(12345L, misRep.get(0).getUnhealthySince());
    assertEquals(13L, misRep.get(0).getContainerID());
    assertEquals("some reason", misRep.get(0).getReason());
  }

}