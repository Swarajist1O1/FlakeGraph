class DummyClass_57244 {
  @Test
  public void testProcessPipelineReport() throws IOException {

    // Check with pipeline which does not exist in Recon.
    Pipeline pipeline = getRandomPipeline();
    PipelineID pipelineID = pipeline.getId();
    HddsProtos.PipelineID pipelineIDProto =  pipelineID.getProtobuf();

    ReconPipelineManager reconPipelineManagerMock = mock(
        ReconPipelineManager.class);
    when(reconPipelineManagerMock.getPipeline(pipelineID)).thenReturn(pipeline);

    StorageContainerServiceProvider scmServiceProviderMock = mock(
        StorageContainerServiceProvider.class);
    when(scmServiceProviderMock.getPipeline(pipelineIDProto))
        .thenReturn(pipeline);

    OzoneConfiguration configuration = new OzoneConfiguration();

    ReconPipelineReportHandler handler =
        new ReconPipelineReportHandler(new ReconSafeModeManager(),
            reconPipelineManagerMock, SCMContext.emptyContext(),
            configuration, scmServiceProviderMock);

    EventPublisher eventPublisherMock = mock(EventPublisher.class);
    PipelineReport report = mock(PipelineReport.class);
    when(report.getPipelineID()).thenReturn(pipelineIDProto);

    handler.processPipelineReport(report, pipeline.getNodes().get(0),
        eventPublisherMock);

    // Verify that the new pipeline was added to pipeline manager.
    verify(reconPipelineManagerMock, times(1))
        .addPipeline(pipeline);
    verify(reconPipelineManagerMock, times(1))
        .getPipeline(pipelineID);

    // Check with pipeline which already exists in Recon.
    pipeline = getRandomPipeline();
    pipelineID = pipeline.getId();
    pipelineIDProto =  pipelineID.getProtobuf();

    when(reconPipelineManagerMock.containsPipeline(pipelineID))
        .thenReturn(true);
    when(reconPipelineManagerMock.getPipeline(pipelineID))
        .thenReturn(pipeline);
    when(report.getPipelineID()).thenReturn(pipelineIDProto);

    handler.processPipelineReport(report, pipeline.getNodes().get(0),
        eventPublisherMock);

    // Verify that the pipeline was not added to pipeline manager.
    verify(reconPipelineManagerMock, times(0))
        .addPipeline(pipeline);
    verify(reconPipelineManagerMock, times(1))
        .getPipeline(pipelineID);
  }

}