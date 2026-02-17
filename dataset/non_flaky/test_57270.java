class DummyClass_57270 {
  @Test
  public void testGetDatanodes() throws Exception {
    Response response = nodeEndpoint.getDatanodes();
    DatanodesResponse datanodesResponse =
        (DatanodesResponse) response.getEntity();
    Assert.assertEquals(2, datanodesResponse.getTotalCount());
    Assert.assertEquals(2, datanodesResponse.getDatanodes().size());

    datanodesResponse.getDatanodes().forEach(datanodeMetadata -> {
      try {
        testDatanodeResponse(datanodeMetadata);
      } catch (IOException e) {
        Assert.fail(e.getMessage());
      }
    });

    waitAndCheckConditionAfterHeartbeat(() -> {
      Response response1 = nodeEndpoint.getDatanodes();
      DatanodesResponse datanodesResponse1 =
          (DatanodesResponse) response1.getEntity();
      DatanodeMetadata datanodeMetadata1 =
          datanodesResponse1.getDatanodes().stream().filter(datanodeMetadata ->
              datanodeMetadata.getHostname().equals("host1.datanode"))
              .findFirst().orElse(null);
      return (datanodeMetadata1 != null &&
          datanodeMetadata1.getContainers() == 1 &&
          datanodeMetadata1.getOpenContainers() == 1 &&
          reconScm.getPipelineManager()
              .getContainersInPipeline(pipeline.getId()).size() == 1);
    });

    // Change Node OperationalState with NodeManager
    final NodeManager nodeManager = reconScm.getScmNodeManager();
    final DatanodeDetails dnDetailsInternal =
        nodeManager.getNodeByUuid(datanodeDetails.getUuidString());
    // Backup existing state and sanity check
    final NodeStatus nStatus = nodeManager.getNodeStatus(dnDetailsInternal);
    final NodeOperationalState backupOpState =
        dnDetailsInternal.getPersistedOpState();
    final long backupOpStateExpiry =
        dnDetailsInternal.getPersistedOpStateExpiryEpochSec();
    assertEquals(backupOpState, nStatus.getOperationalState());
    assertEquals(backupOpStateExpiry, nStatus.getOpStateExpiryEpochSeconds());

    dnDetailsInternal.setPersistedOpState(NodeOperationalState.DECOMMISSIONING);
    dnDetailsInternal.setPersistedOpStateExpiryEpochSec(666L);
    nodeManager.setNodeOperationalState(dnDetailsInternal,
        NodeOperationalState.DECOMMISSIONING, 666L);
    // Check if the endpoint response reflects the change
    response = nodeEndpoint.getDatanodes();
    datanodesResponse = (DatanodesResponse) response.getEntity();
    // Order of datanodes in the response is random
    AtomicInteger count = new AtomicInteger();
    datanodesResponse.getDatanodes().forEach(metadata -> {
      if (metadata.getUuid().equals(dnDetailsInternal.getUuidString())) {
        count.incrementAndGet();
        assertEquals(NodeOperationalState.DECOMMISSIONING,
            metadata.getOperationalState());
      }
    });
    assertEquals(1, count.get());

    // Restore state
    dnDetailsInternal.setPersistedOpState(backupOpState);
    dnDetailsInternal.setPersistedOpStateExpiryEpochSec(backupOpStateExpiry);
    nodeManager.setNodeOperationalState(dnDetailsInternal,
        backupOpState, backupOpStateExpiry);
  }

}