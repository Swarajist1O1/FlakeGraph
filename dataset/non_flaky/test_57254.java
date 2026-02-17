class DummyClass_57254 {
  @Test
  public void testUpdateContainerStateFromOpen() throws Exception {
    ContainerWithPipeline containerWithPipeline =
        getTestContainer(LifeCycleState.OPEN);
    ContainerID containerID =
        containerWithPipeline.getContainerInfo().containerID();

    // Adding container #100.
    getContainerManager().addNewContainer(containerWithPipeline);
    assertEquals(LifeCycleState.OPEN,
        getContainerManager().getContainer(containerID).getState());

    DatanodeDetails datanodeDetails = randomDatanodeDetails();

    // First report with "CLOSED" replica state moves container state to
    // "CLOSING".
    getContainerManager().checkAndAddNewContainer(containerID, State.CLOSED,
        datanodeDetails);
    assertEquals(CLOSING,
        getContainerManager().getContainer(containerID).getState());
  }

}