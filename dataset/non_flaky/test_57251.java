class DummyClass_57251 {
  @Test
  public void testAddNewClosedContainer() throws IOException {
    ContainerWithPipeline containerWithPipeline = getTestContainer(CLOSED);
    ContainerID containerID =
        containerWithPipeline.getContainerInfo().containerID();
    ContainerInfo containerInfo = containerWithPipeline.getContainerInfo();

    ReconContainerManager containerManager = getContainerManager();
    assertFalse(containerManager.containerExist(containerID));
    assertFalse(getContainerTable().isExist(containerID));

    containerManager.addNewContainer(containerWithPipeline);

    assertTrue(containerManager.containerExist(containerID));

    List<ContainerInfo> containers = containerManager.getContainers(CLOSED);
    assertEquals(1, containers.size());
    assertEquals(containerInfo, containers.get(0));
    // Verify container DB.
    SCMHAManager scmhaManager = containerManager.getSCMHAManager();
    scmhaManager.getDBTransactionBuffer().close();
    assertTrue(getContainerTable().isExist(containerID));
  }

}