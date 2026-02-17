class DummyClass_57248 {
  @Test
  public void testUpdateNodeOperationalStateFromScm() throws Exception {
    ReconStorageConfig scmStorageConfig = new ReconStorageConfig(conf);
    EventQueue eventQueue = new EventQueue();
    NetworkTopology clusterMap = new NetworkTopologyImpl(conf);
    Table<UUID, DatanodeDetails> nodeTable =
        ReconSCMDBDefinition.NODES.getTable(store);
    ReconNodeManager reconNodeManager = new ReconNodeManager(conf,
        scmStorageConfig, eventQueue, clusterMap, nodeTable, versionManager);


    DatanodeDetails datanodeDetails = randomDatanodeDetails();
    HddsProtos.Node node = mock(HddsProtos.Node.class);

    LambdaTestUtils.intercept(NodeNotFoundException.class, () -> {
      reconNodeManager.updateNodeOperationalStateFromScm(node, datanodeDetails);
    });

    reconNodeManager.register(datanodeDetails, null, null);
    assertEquals(IN_SERVICE, reconNodeManager
        .getNodeByUuid(datanodeDetails.getUuidString()).getPersistedOpState());

    when(node.getNodeOperationalStates(eq(0)))
        .thenReturn(DECOMMISSIONING);
    reconNodeManager.updateNodeOperationalStateFromScm(node, datanodeDetails);
    assertEquals(DECOMMISSIONING, reconNodeManager
        .getNodeByUuid(datanodeDetails.getUuidString()).getPersistedOpState());
    List<DatanodeDetails> nodes =
        reconNodeManager.getNodes(DECOMMISSIONING, null);
    assertEquals(1, nodes.size());
    assertEquals(datanodeDetails.getUuid(), nodes.get(0).getUuid());
  }

}