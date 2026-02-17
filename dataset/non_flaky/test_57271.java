class DummyClass_57271 {
  @Test
  public void testGetPipelines() throws Exception {
    Response response = pipelineEndpoint.getPipelines();
    PipelinesResponse pipelinesResponse =
        (PipelinesResponse) response.getEntity();
    Assert.assertEquals(1, pipelinesResponse.getTotalCount());
    Assert.assertEquals(1, pipelinesResponse.getPipelines().size());
    PipelineMetadata pipelineMetadata =
        pipelinesResponse.getPipelines().iterator().next();
    Assert.assertEquals(1, pipelineMetadata.getDatanodes().size());
    Assert.assertEquals(pipeline.getType().toString(),
        pipelineMetadata.getReplicationType());
    Assert.assertEquals(pipeline.getReplicationConfig().getRequiredNodes(),
        pipelineMetadata.getReplicationFactor());
    Assert.assertEquals(datanodeDetails.getHostName(),
        pipelineMetadata.getLeaderNode());
    Assert.assertEquals(pipeline.getId().getId(),
        pipelineMetadata.getPipelineId());
    Assert.assertEquals(5, pipelineMetadata.getLeaderElections());

    waitAndCheckConditionAfterHeartbeat(() -> {
      Response response1 = pipelineEndpoint.getPipelines();
      PipelinesResponse pipelinesResponse1 =
          (PipelinesResponse) response1.getEntity();
      PipelineMetadata pipelineMetadata1 =
          pipelinesResponse1.getPipelines().iterator().next();
      return (pipelineMetadata1.getContainers() == 1);
    });
  }

}