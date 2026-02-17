class DummyClass_57273 {
  @Test
  public void testGetClusterState() throws Exception {
    Response response = clusterStateEndpoint.getClusterState();
    ClusterStateResponse clusterStateResponse =
        (ClusterStateResponse) response.getEntity();

    Assert.assertEquals(1, clusterStateResponse.getPipelines());
    Assert.assertEquals(0, clusterStateResponse.getVolumes());
    Assert.assertEquals(0, clusterStateResponse.getBuckets());
    Assert.assertEquals(0, clusterStateResponse.getKeys());
    Assert.assertEquals(2, clusterStateResponse.getTotalDatanodes());
    Assert.assertEquals(2, clusterStateResponse.getHealthyDatanodes());

    waitAndCheckConditionAfterHeartbeat(() -> {
      Response response1 = clusterStateEndpoint.getClusterState();
      ClusterStateResponse clusterStateResponse1 =
          (ClusterStateResponse) response1.getEntity();
      return (clusterStateResponse1.getContainers() == 1);
    });

    // check volume, bucket and key count after running table count task
    Pair<String, Boolean> result =
        tableCountTask.reprocess(reconOMMetadataManager);
    assertTrue(result.getRight());
    response = clusterStateEndpoint.getClusterState();
    clusterStateResponse = (ClusterStateResponse) response.getEntity();
    Assert.assertEquals(2, clusterStateResponse.getVolumes());
    Assert.assertEquals(2, clusterStateResponse.getBuckets());
    Assert.assertEquals(3, clusterStateResponse.getKeys());
  }

}