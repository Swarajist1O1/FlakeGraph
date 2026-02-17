class DummyClass_57247 {
  @Test
  public void testReconNodeDB() throws IOException, NodeNotFoundException {
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
    String uuidString = datanodeDetails.getUuidString();

    // Register a random datanode.
    reconNodeManager.register(datanodeDetails, null, null);
    reconNewNodeHandler.onMessage(reconNodeManager.getNodeByUuid(uuidString),
        null);

    assertEquals(1, reconNodeManager.getAllNodes().size());
    assertNotNull(reconNodeManager.getNodeByUuid(uuidString));

    // If any commands are added to the eventQueue without using the onMessage
    // interface, then they should be filtered out and not returned to the DN
    // when it heartbeats.
    // This command should never be returned by Recon
    reconNodeManager.addDatanodeCommand(datanodeDetails.getUuid(),
        new SetNodeOperationalStateCommand(1234,
        DECOMMISSIONING, 0));

    // This one should be returned
    reconNodeManager.addDatanodeCommand(datanodeDetails.getUuid(),
        new ReregisterCommand());

    // OperationalState sanity check
    final DatanodeDetails dnDetails =
        reconNodeManager.getNodeByUuid(datanodeDetails.getUuidString());
    assertEquals(HddsProtos.NodeOperationalState.IN_SERVICE,
        dnDetails.getPersistedOpState());
    assertEquals(dnDetails.getPersistedOpState(),
        reconNodeManager.getNodeStatus(dnDetails)
            .getOperationalState());
    assertEquals(dnDetails.getPersistedOpStateExpiryEpochSec(),
        reconNodeManager.getNodeStatus(dnDetails)
            .getOpStateExpiryEpochSeconds());

    // Upon processing the heartbeat, the illegal command should be filtered out
    List<SCMCommand> returnedCmds =
        reconNodeManager.processHeartbeat(datanodeDetails,
            defaultLayoutVersionProto());
    assertEquals(1, returnedCmds.size());
    assertEquals(SCMCommandProto.Type.reregisterCommand,
        returnedCmds.get(0).getType());

    // Now feed a DECOMMISSIONED heartbeat of the same DN
    datanodeDetails.setPersistedOpState(
        HddsProtos.NodeOperationalState.DECOMMISSIONED);
    datanodeDetails.setPersistedOpStateExpiryEpochSec(12345L);
    reconNodeManager.processHeartbeat(datanodeDetails,
        defaultLayoutVersionProto());
    // Check both persistedOpState and NodeStatus#operationalState
    assertEquals(HddsProtos.NodeOperationalState.DECOMMISSIONED,
        dnDetails.getPersistedOpState());
    assertEquals(dnDetails.getPersistedOpState(),
        reconNodeManager.getNodeStatus(dnDetails)
            .getOperationalState());
    assertEquals(12345L, dnDetails.getPersistedOpStateExpiryEpochSec());
    assertEquals(dnDetails.getPersistedOpStateExpiryEpochSec(),
        reconNodeManager.getNodeStatus(dnDetails)
            .getOpStateExpiryEpochSeconds());

    // Close the DB, and recreate the instance of Recon Node Manager.
    eventQueue.close();
    reconNodeManager.close();
    reconNodeManager = new ReconNodeManager(conf, scmStorageConfig, eventQueue,
        clusterMap, nodeTable, versionManager);

    // Verify that the node information was persisted and loaded back.
    assertEquals(1, reconNodeManager.getAllNodes().size());
    assertNotNull(
        reconNodeManager.getNodeByUuid(datanodeDetails.getUuidString()));
  }

}