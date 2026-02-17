class DummyClass_57257 {
  @Test
  public void testAddPipeline() throws IOException {

    Pipeline pipeline = getRandomPipeline();
    NetworkTopology clusterMap = new NetworkTopologyImpl(conf);
    EventQueue eventQueue = new EventQueue();
    this.versionManager =
        Mockito.mock(HDDSLayoutVersionManager.class);
    Mockito.when(versionManager.getMetadataLayoutVersion())
        .thenReturn(maxLayoutVersion());
    Mockito.when(versionManager.getSoftwareLayoutVersion())
        .thenReturn(maxLayoutVersion());
    NodeManager nodeManager = new SCMNodeManager(conf, scmStorageConfig,
        eventQueue, clusterMap, SCMContext.emptyContext(), versionManager);

    ReconPipelineManager reconPipelineManager =
        ReconPipelineManager.newReconPipelineManager(
            conf,
            nodeManager,
            ReconSCMDBDefinition.PIPELINES.getTable(store),
            eventQueue,
            scmhaManager,
            scmContext);

    assertFalse(reconPipelineManager.containsPipeline(pipeline.getId()));
    reconPipelineManager.addPipeline(pipeline);
    assertTrue(reconPipelineManager.containsPipeline(pipeline.getId()));
  }

}