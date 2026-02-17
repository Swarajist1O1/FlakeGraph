class DummyClass_57252 {
  @Test
  public void testCheckAndAddNewContainer() throws Exception {
    ContainerID containerID = ContainerID.valueOf(100L);
    ReconContainerManager containerManager = getContainerManager();
    assertFalse(containerManager.containerExist(containerID));
    DatanodeDetails datanodeDetails = randomDatanodeDetails();
    containerManager.checkAndAddNewContainer(containerID,
        OPEN, datanodeDetails);
    assertTrue(containerManager.containerExist(containerID));

    // Doing it one more time should not change any state.
    containerManager.checkAndAddNewContainer(containerID, OPEN,
        datanodeDetails);
    assertTrue(containerManager.containerExist(containerID));
    assertEquals(LifeCycleState.OPEN,
        getContainerManager().getContainer(containerID).getState());
  }

}