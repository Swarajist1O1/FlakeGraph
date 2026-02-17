class DummyClass_57291 {
  @Test
  public void testGetPipeline() throws IOException {
    StorageContainerServiceProvider scmProvider =
        injector.getInstance(StorageContainerServiceProvider.class);
    StorageContainerLocationProtocol scmClient =
        injector.getInstance(StorageContainerLocationProtocol.class);
    Pipeline pipeline = scmProvider.getPipeline(pipelineID);
    assertNotNull(pipeline);
    verify(scmClient, times(1))
        .getPipeline(pipelineID);
  }

}