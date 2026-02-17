class DummyClass_57281 {
  @Test
  public void testGetMissingContainers() throws IOException {
    Response response = containerEndpoint.getMissingContainers();

    MissingContainersResponse responseObject =
        (MissingContainersResponse) response.getEntity();

    assertEquals(0, responseObject.getTotalCount());
    assertEquals(Collections.EMPTY_LIST, responseObject.getContainers());

    // Add missing containers to the database
    long missingSince = System.currentTimeMillis();
    UnhealthyContainers missing = new UnhealthyContainers();
    missing.setContainerId(1L);
    missing.setInStateSince(missingSince);
    missing.setActualReplicaCount(0);
    missing.setExpectedReplicaCount(3);
    missing.setReplicaDelta(3);
    missing.setContainerState(
        ContainerSchemaDefinition.UnHealthyContainerStates.MISSING.toString());
    ArrayList<UnhealthyContainers> missingList =
        new ArrayList<UnhealthyContainers>();
    missingList.add(missing);
    containerHealthSchemaManager.insertUnhealthyContainerRecords(missingList);

    putContainerInfos(1);
    // Add container history for id 1
    final UUID u1 = newDatanode("host1", "127.0.0.1");
    final UUID u2 = newDatanode("host2", "127.0.0.2");
    final UUID u3 = newDatanode("host3", "127.0.0.3");
    final UUID u4 = newDatanode("host4", "127.0.0.4");
    reconContainerManager.upsertContainerHistory(1L, u1, 1L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u2, 2L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u3, 3L, 1L);
    reconContainerManager.upsertContainerHistory(1L, u4, 4L, 1L);

    response = containerEndpoint.getMissingContainers();
    responseObject = (MissingContainersResponse) response.getEntity();
    assertEquals(1, responseObject.getTotalCount());
    MissingContainerMetadata container =
        responseObject.getContainers().stream().findFirst().orElse(null);
    Assert.assertNotNull(container);

    assertEquals(containerID.getId(), container.getContainerID());
    assertEquals(keyCount, container.getKeys());
    assertEquals(pipelineID.getId(), container.getPipelineID());
    assertEquals(3, container.getReplicas().size());
    assertEquals(missingSince, container.getMissingSince());

    Set<String> datanodes = Collections.unmodifiableSet(
        new HashSet<>(Arrays.asList("host2", "host3", "host4")));
    List<ContainerHistory> containerReplicas = container.getReplicas();
    containerReplicas.forEach(history -> {
      Assert.assertTrue(datanodes.contains(history.getDatanodeHost()));
    });
  }

}