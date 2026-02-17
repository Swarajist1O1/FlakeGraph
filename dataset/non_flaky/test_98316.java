class DummyClass_98316 {
  @Test
  public void testOneHeader() throws Exception {
    builder.header("foo", 1);

    final DefaultDockerClient dockerClient = new DefaultDockerClient(
        builder, clientBuilderSupplier);
    dockerClient.info();

    final ArgumentCaptor<String> keyArgument = ArgumentCaptor.forClass(String.class);
    final ArgumentCaptor<Object> valueArgument = ArgumentCaptor.forClass(Object.class);
    verify(builderMock, times(1)).header(keyArgument.capture(), valueArgument.capture());

    Assert.assertEquals("foo", keyArgument.getValue());
    Assert.assertEquals(1, valueArgument.getValue());
  }

}