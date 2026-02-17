class DummyClass_98315 {
  @Test
  public void testNoHeaders() throws Exception {
    final DefaultDockerClient dockerClient = new DefaultDockerClient(
        builder, clientBuilderSupplier);
    dockerClient.info();

    verify(builderMock, never()).header(anyString(), anyString());
  }

}