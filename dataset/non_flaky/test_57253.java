class DummyClass_57253 {
  @Test
  public void testCheckAndAddNewContainerBatch() throws IOException {
    List<ContainerReplicaProto> containerReplicaProtoList = new LinkedList<>();
    ReconContainerManager containerManager = getContainerManager();
    State[] stateTypes = State.values();
    LifeCycleState[] lifeCycleStateTypes = LifeCycleState.values();
    int lifeCycleStateCount = lifeCycleStateTypes.length;
    for (int i = 200; i < 300; i++) {
      assertFalse(containerManager.containerExist(ContainerID.valueOf(i)));
      ContainerReplicaProto.Builder ciBuilder =
          ContainerReplicaProto.newBuilder();
      ContainerReplicaProto crp = ciBuilder.
          setContainerID(i).
          setState(stateTypes[i % lifeCycleStateCount]).build();
      containerReplicaProtoList.add(crp);
    }

    containerManager.checkAndAddNewContainerBatch(containerReplicaProtoList);
    for (long i = 200L; i < 300L; i++) {
      assertTrue(containerManager.containerExist(ContainerID.valueOf(i)));
    }

    // Doing it one more time should not change any state.
    containerManager.checkAndAddNewContainerBatch(containerReplicaProtoList);
    for (int i = 200; i < 300; i++) {
      assertTrue(containerManager.containerExist(ContainerID.valueOf(i)));
      assertEquals(lifeCycleStateTypes[i % lifeCycleStateCount],
          getContainerManager().
            getContainer(ContainerID.valueOf(i)).getState());
    }
  }

}