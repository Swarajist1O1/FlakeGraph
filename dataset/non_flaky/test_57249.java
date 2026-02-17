class DummyClass_57249 {
  @Test
  public void testDatanodeUpdate() throws IOException {
    ReconStorageConfig scmStorageConfig = new ReconStorageConfig(conf);
    EventQueue eventQueue = new EventQueue();
    NetworkTopology clusterMap = new NetworkTopologyImpl(conf);
    Table<UUID, DatanodeDetails> nodeTable =
        ReconSCMDBDefinition.NODES.getTable(store);
    ReconNodeManager reconNodeManager = new ReconNodeManager(conf,
        scmStorageConfig, eventQueue, clusterMap, nodeTable, versionManager);
    ReconNewNodeHandler reconNewNodeHandler =
        new ReconNewNodeHandler(reconNodeManager);
    assertTrue(reconNodeManager.getAllNodes().isEmpty());

    DatanodeDetails datanodeDetails = randomDatanodeDetails();
    datanodeDetails.setHostName("hostname1");
    String uuidString = datanodeDetails.getUuidString();

    // Register "hostname1" datanode.
    reconNodeManager.register(datanodeDetails, null, null);
    reconNewNodeHandler.onMessage(reconNodeManager.getNodeByUuid(uuidString),
        null);

    assertEquals(1, reconNodeManager.getAllNodes().size());
    assertNotNull(reconNodeManager.getNodeByUuid(uuidString));
    assertEquals("hostname1",
        reconNodeManager.getNodeByUuid(uuidString).getHostName());

    datanodeDetails.setHostName("hostname2");
    // Upon processing the heartbeat, the illegal command should be filtered out
    List<SCMCommand> returnedCmds =
        reconNodeManager.processHeartbeat(datanodeDetails,
            defaultLayoutVersionProto());
    assertEquals(1, returnedCmds.size());
    assertEquals(SCMCommandProto.Type.reregisterCommand,
        returnedCmds.get(0).getType());

  }

}