class DummyClass_57243 {
  @Test
  public void testDeletedContainer() throws Exception {
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

    // Create 2 containers. The first is OPEN will no replicas, the second is
    // CLOSED with no replicas.
    List<ContainerInfo> mockContainers = getMockContainers(2);
    when(scmMock.getScmServiceProvider()).thenReturn(scmClientMock);
    when(scmMock.getContainerManager()).thenReturn(containerManagerMock);
    when(containerManagerMock.getContainers()).thenReturn(mockContainers);
    for (ContainerInfo c : mockContainers) {
      when(containerManagerMock.getContainer(c.containerID())).thenReturn(c);
      when(scmClientMock.getContainerWithPipeline(c.getContainerID()))
          .thenReturn(new ContainerWithPipeline(c, null));
    }
    // Container State OPEN with no replicas
    when(containerManagerMock.getContainer(ContainerID.valueOf(1L)).getState())
        .thenReturn(HddsProtos.LifeCycleState.OPEN);
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(1L)))
        .thenReturn(Collections.emptySet());
    when(scmClientMock.getContainerWithPipeline(1))
        .thenReturn(new ContainerWithPipeline(mockContainers.get(0), null));

    // Container State CLOSED with no replicas
    when(containerManagerMock.getContainer(ContainerID.valueOf(2L)).getState())
        .thenReturn(HddsProtos.LifeCycleState.CLOSED);
    when(containerManagerMock.getContainerReplicas(ContainerID.valueOf(2L)))
        .thenReturn(Collections.emptySet());
    ContainerInfo mockDeletedContainer = getMockDeletedContainer(2);
    when(scmClientMock.getContainerWithPipeline(2))
        .thenReturn(new ContainerWithPipeline(mockDeletedContainer, null));

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
        (unHealthyContainersTableHandle.count() == 1));
    UnhealthyContainers rec =
        unHealthyContainersTableHandle.fetchByContainerId(1L).get(0);
    assertEquals("MISSING", rec.getContainerState());
    assertEquals(3, rec.getReplicaDelta().intValue());

    ReconTaskStatus taskStatus =
        reconTaskStatusDao.findById(containerHealthTask.getTaskName());
    Assert.assertTrue(taskStatus.getLastUpdatedTimestamp() >
        currentTime);
  }

}