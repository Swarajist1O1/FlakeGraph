class DummyClass_57258 {
  @Test
  public void testStubbedReconPipelineFactory() throws IOException {

    NodeManager nodeManagerMock = mock(NodeManager.class);

    ReconPipelineManager reconPipelineManager =
        ReconPipelineManager.newReconPipelineManager(
            conf,
            nodeManagerMock,
            ReconSCMDBDefinition.PIPELINES.getTable(store),
            new EventQueue(),
            scmhaManager,
            scmContext);

    PipelineFactory pipelineFactory = reconPipelineManager.getPipelineFactory();
    assertTrue(pipelineFactory instanceof ReconPipelineFactory);
    ReconPipelineFactory reconPipelineFactory =
        (ReconPipelineFactory) pipelineFactory;
    assertTrue(reconPipelineFactory.getProviders().isEmpty());
    for (ReplicationType type  : reconPipelineFactory.getProviders().keySet()) {
      PipelineProvider pipelineProvider =
          reconPipelineFactory.getProviders().get(type);
      assertTrue(pipelineProvider instanceof ReconPipelineProvider);
    }
  }

}