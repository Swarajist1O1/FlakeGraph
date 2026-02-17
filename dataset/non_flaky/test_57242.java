class DummyClass_57242 {
  @Test
  public void testRun() throws Exception {
    UnhealthyContainersDao unHealthyContainersTableHandle =
        getDao(UnhealthyContainersDao.class);

    ContainerHealthSchemaManager containerHealthSchemaManager =
        new ContainerHealthSchemaManager(
            getSchemaDefinition(ContainerSchemaDefinition.class),
            unHealthyContainersTableHandle);
    ReconStorageContainerManagerFacade scmMock =
        mock(ReconStorageContainerManagerFacade.class);
    MockPlacementPolicy placementMock = new MockPlacementPolicy();
    ContainerManager containerManagerMock = mock(ContainerManager.class);
    StorageContainerServiceProvider scmClientMock =
        mock(StorageContainerServiceProvider.class);
    ContainerReplica unhealthyReplicaMock = mock(ContainerReplica.class);
    when(unhealthyReplicaMock.getState()).thenReturn(State.UNHEALTHY);
    ContainerReplica healthyReplicaMock = mock(ContainerReplica.class);
    when(healthyReplicaMock.getState()).thenReturn(State.CLOSED);

    // Create 6 containers. The first 5 will have various unhealthy states
    // defined below. The container with ID=6 will be healthy.
    List<ContainerInfo> mockContainers = getMockContainers(6);
    when(scmMock.getScmServiceProvider()).thenReturn(scmClientMock);
    when(scmMock.getContainerManager()).thenReturn(containerManagerMock);
    when(containerManagerMock.getContainers()).thenReturn(mockContainers);
    for (ContainerInfo c : mockContainers) {
      when(containerManagerMock.getContainer(c.containerID())).thenReturn(c);
      when(scmClientMock.getContainerWithPipeline(c.getContainerID()))
          .thenReturn(new ContainerWithPipeline(c, null));
    }
    // Under replicated
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(1L)))
        .thenReturn(getMockReplicas(1L, State.CLOSED, State.UNHEALTHY));

    // return all UNHEALTHY replicas for container ID 2 -> UNDER_REPLICATED
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(2L)))
        .thenReturn(getMockReplicas(2L, State.UNHEALTHY));

    // return 0 replicas for container ID 3 -> Missing
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(3L)))
        .thenReturn(Collections.emptySet());

    // Return 5 Healthy -> Over replicated
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(4L)))
        .thenReturn(getMockReplicas(4L, State.CLOSED, State.CLOSED,
        State.CLOSED, State.CLOSED, State.CLOSED));

    // Mis-replicated
    Set<ContainerReplica> misReplicas = getMockReplicas(5L,
        State.CLOSED, State.CLOSED, State.CLOSED);
    placementMock.setMisRepWhenDnPresent(
        misReplicas.iterator().next().getDatanodeDetails().getUuid());
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(5L)))
        .thenReturn(misReplicas);

    // Return 3 Healthy -> Healthy container
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(6L)))
        .thenReturn(getMockReplicas(6L,
            State.CLOSED, State.CLOSED, State.CLOSED));

    List<UnhealthyContainers> all = unHealthyContainersTableHandle.findAll();
    Assert.assertTrue(all.isEmpty());

    long currentTime = System.currentTimeMillis();
    ReconTaskStatusDao reconTaskStatusDao = getDao(ReconTaskStatusDao.class);
    ReconTaskConfig reconTaskConfig = new ReconTaskConfig();
    reconTaskConfig.setMissingContainerTaskInterval(Duration.ofSeconds(2));
    ContainerHealthTask containerHealthTask =
        new ContainerHealthTask(scmMock.getContainerManager(),
            scmMock.getScmServiceProvider(),
            reconTaskStatusDao, containerHealthSchemaManager,
            placementMock, reconTaskConfig);
    containerHealthTask.start();
    LambdaTestUtils.await(6000, 1000, () ->
        (unHealthyContainersTableHandle.count() == 5));
    UnhealthyContainers rec =
        unHealthyContainersTableHandle.fetchByContainerId(1L).get(0);
    assertEquals("UNDER_REPLICATED", rec.getContainerState());
    assertEquals(2, rec.getReplicaDelta().intValue());

    rec = unHealthyContainersTableHandle.fetchByContainerId(2L).get(0);
    assertEquals("UNDER_REPLICATED", rec.getContainerState());
    assertEquals(3, rec.getReplicaDelta().intValue());

    List<UnhealthyContainers> unhealthyContainers =
        containerHealthSchemaManager.getUnhealthyContainers(
            ALL_REPLICAS_UNHEALTHY, 0, Integer.MAX_VALUE);
    assertEquals(1, unhealthyContainers.size());
    assertEquals(2L,
        unhealthyContainers.get(0).getContainerId().longValue());
    assertEquals(0,
        unhealthyContainers.get(0).getActualReplicaCount().intValue());

    rec = unHealthyContainersTableHandle.fetchByContainerId(3L).get(0);
    assertEquals("MISSING", rec.getContainerState());
    assertEquals(3, rec.getReplicaDelta().intValue());

    rec = unHealthyContainersTableHandle.fetchByContainerId(4L).get(0);
    assertEquals("OVER_REPLICATED", rec.getContainerState());
    assertEquals(-2, rec.getReplicaDelta().intValue());

    rec = unHealthyContainersTableHandle.fetchByContainerId(5L).get(0);
    assertEquals("MIS_REPLICATED", rec.getContainerState());
    assertEquals(1, rec.getReplicaDelta().intValue());
    assertEquals(2, rec.getExpectedReplicaCount().intValue());
    assertEquals(1, rec.getActualReplicaCount().intValue());
    assertNotNull(rec.getReason());

    ReconTaskStatus taskStatus =
        reconTaskStatusDao.findById(containerHealthTask.getTaskName());
    Assert.assertTrue(taskStatus.getLastUpdatedTimestamp() >
        currentTime);

    // Now run the job again, to check that relevant records are updated or
    // removed as appropriate. Need to adjust the return value for all the mocks
    // Under replicated -> Delta goes from 2 to 1
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(1L)))
        .thenReturn(getMockReplicas(1L, State.CLOSED, State.CLOSED));

    // ID 2 was missing - make it healthy now
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(2L)))
        .thenReturn(getMockReplicas(2L,
            State.CLOSED, State.CLOSED, State.CLOSED));

    // return 0 replicas for container ID 3 -> Still Missing
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(3L)))
        .thenReturn(Collections.emptySet());

    // Return 4 Healthy -> Delta changes from -2 to -1
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(4L)))
        .thenReturn(getMockReplicas(4L, State.CLOSED, State.CLOSED,
            State.CLOSED, State.CLOSED));

    // Was mis-replicated - make it healthy now
    placementMock.setMisRepWhenDnPresent(null);

    LambdaTestUtils.await(6000, 1000, () ->
        (unHealthyContainersTableHandle.count() == 3));
    rec = unHealthyContainersTableHandle.fetchByContainerId(1L).get(0);
    assertEquals("UNDER_REPLICATED", rec.getContainerState());
    assertEquals(1, rec.getReplicaDelta().intValue());

    // This container is now healthy, it should not be in the table any more
    assertEquals(0,
        unHealthyContainersTableHandle.fetchByContainerId(2L).size());

    rec = unHealthyContainersTableHandle.fetchByContainerId(3L).get(0);

    assertEquals("MISSING", rec.getContainerState());
    assertEquals(3, rec.getReplicaDelta().intValue());

    rec = unHealthyContainersTableHandle.fetchByContainerId(4L).get(0);
    assertEquals("OVER_REPLICATED", rec.getContainerState());
    assertEquals(-1, rec.getReplicaDelta().intValue());

    // This container is now healthy, it should not be in the table any more
    assertEquals(0,
        unHealthyContainersTableHandle.fetchByContainerId(5L).size());
  }

}