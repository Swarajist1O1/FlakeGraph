class DummyClass_57246 {
  @Test
  public void testProcessICRStateMismatch() throws IOException {

    // Recon container state is "OPEN".
    // Replica state could be any Non OPEN state.
    long containerId = 11;
    for (State state : Arrays.asList(State.CLOSING, State.QUASI_CLOSED,
        State.CLOSED)) {
      ContainerWithPipeline containerWithPipeline = getTestContainer(
          containerId++, OPEN);
      ContainerID containerID =
          containerWithPipeline.getContainerInfo().containerID();

      ReconContainerManager containerManager = getContainerManager();
      containerManager.addNewContainer(containerWithPipeline);

      DatanodeDetails datanodeDetails =
          containerWithPipeline.getPipeline().getFirstNode();
      NodeManager nodeManagerMock = mock(NodeManager.class);
      when(nodeManagerMock.getNodeByUuid(any())).thenReturn(datanodeDetails);
      IncrementalContainerReportFromDatanode reportMock =
          mock(IncrementalContainerReportFromDatanode.class);
      when(reportMock.getDatanodeDetails())
          .thenReturn(containerWithPipeline.getPipeline().getFirstNode());

      IncrementalContainerReportProto containerReport =
          getIncrementalContainerReportProto(containerID, state,
              datanodeDetails.getUuidString());
      when(reportMock.getReport()).thenReturn(containerReport);
      ReconIncrementalContainerReportHandler reconIcr =
          new ReconIncrementalContainerReportHandler(nodeManagerMock,
              containerManager, SCMContext.emptyContext());

      reconIcr.onMessage(reportMock, mock(EventPublisher.class));
      assertTrue(containerManager.containerExist(containerID));
      assertEquals(1,
          containerManager.getContainerReplicas(containerID).size());
      LifeCycleState expectedState = getContainerStateFromReplicaState(state);
      LifeCycleState actualState =
          containerManager.getContainer(containerID).getState();
      assertEquals(String.format("Expecting %s in " +
              "container state for replica state %s", expectedState,
          state), expectedState, actualState);
    }
  }

}