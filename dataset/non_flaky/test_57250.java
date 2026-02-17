class DummyClass_57250 {
  @Test
  public void testAddNewOpenContainer() throws IOException {
    ContainerWithPipeline containerWithPipeline =
        getTestContainer(LifeCycleState.OPEN);
    ContainerID containerID =
        containerWithPipeline.getContainerInfo().containerID();
    ContainerInfo containerInfo = containerWithPipeline.getContainerInfo();

    ReconContainerManager containerManager = getContainerManager();
    assertFalse(containerManager.containerExist(containerID));
    assertFalse(getContainerTable().isExist(containerID));

    containerManager.addNewContainer(containerWithPipeline);

    assertTrue(containerManager.containerExist(containerID));

    List<ContainerInfo> containers =
        containerManager.getContainers(LifeCycleState.OPEN);
    assertEquals(1, containers.size());
    assertEquals(containerInfo, containers.get(0));
    NavigableSet<ContainerID> containersInPipeline =
        getPipelineManager().getContainersInPipeline(
            containerWithPipeline.getPipeline().getId());
    assertEquals(1, containersInPipeline.size());
    assertEquals(containerID, containersInPipeline.first());

    // Verify container DB.
    SCMHAManager scmhaManager = containerManager.getSCMHAManager();
    scmhaManager.getDBTransactionBuffer().close();
    assertTrue(getContainerTable().isExist(containerID));
  }

}