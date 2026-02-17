class DummyClass_57290 {
  @Test
  public void testGetPipelines() throws IOException {
    StorageContainerServiceProvider scmProvider =
        injector.getInstance(StorageContainerServiceProvider.class);
    StorageContainerLocationProtocol scmClient =
        injector.getInstance(StorageContainerLocationProtocol.class);
    scmProvider.getPipelines();
    verify(scmClient, times(1)).listPipelines();
  }

}