class DummyClass_57256 {
  @Test
  public void testInitialize() throws IOException {

    // Get 3 OPEN pipelines from SCM.
    List<Pipeline> pipelinesFromScm = getPipelines(3);

    // Recon has 2 pipelines in ALLOCATED state. (1 is valid and 1 is obsolete)

    // Valid pipeline in Allocated state.
    Pipeline validPipeline = Pipeline.newBuilder()
        .setReplicationConfig(
            new StandaloneReplicationConfig(ReplicationFactor.ONE))
        .setId(pipelinesFromScm.get(0).getId())
        .setNodes(pipelinesFromScm.get(0).getNodes())
        .setState(Pipeline.PipelineState.ALLOCATED)

        .build();

    // Invalid pipeline.
    Pipeline invalidPipeline = Pipeline.newBuilder()
        .setReplicationConfig(
            new StandaloneReplicationConfig(ReplicationFactor.ONE))
        .setId(PipelineID.randomId())
        .setNodes(Collections.singletonList(randomDatanodeDetails()))
        .setState(Pipeline.PipelineState.CLOSED)
        .build();

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

    try (ReconPipelineManager reconPipelineManager =
             ReconPipelineManager.newReconPipelineManager(
                 conf,
                 nodeManager,
                 ReconSCMDBDefinition.PIPELINES.getTable(store),
                 eventQueue,
                 scmhaManager,
                 scmContext)) {
      scmContext = new SCMContext.Builder().setIsInSafeMode(true)
              .setLeader(true).setIsPreCheckComplete(true)
              .setSCM(mock(StorageContainerManager.class)).build();
      reconPipelineManager.setScmContext(scmContext);
      reconPipelineManager.addPipeline(validPipeline);
      reconPipelineManager.addPipeline(invalidPipeline);

      reconPipelineManager.initializePipelines(pipelinesFromScm);
      List<Pipeline> newReconPipelines = reconPipelineManager.getPipelines();

      // Test if the number of pipelines in SCM is as expected.
      assertEquals(3, newReconPipelines.size());

      // Test if new pipelines from SCM are picked up.
      for (Pipeline pipeline : pipelinesFromScm) {
        assertTrue(reconPipelineManager.containsPipeline(pipeline.getId()));
      }

      // Test if existing pipeline state is updated.
      assertEquals(Pipeline.PipelineState.OPEN, reconPipelineManager
          .getPipeline(validPipeline.getId()).getPipelineState());

      // Test if obsolete pipelines in Recon are removed.
      assertFalse(reconPipelineManager.containsPipeline(
          invalidPipeline.getId()));
    }
  }

}