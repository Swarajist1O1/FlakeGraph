class DummyClass_98318 {
  @Test
  public void testCapAddAndDrop() throws Exception {
    final DefaultDockerClient dockerClient = new DefaultDockerClient(
        builder, clientBuilderSupplier);

    final HostConfig hostConfig = HostConfig.builder()
        .capAdd(ImmutableList.of("foo", "bar"))
        .capAdd(ImmutableList.of("baz", "qux"))
        .build();

    final ContainerConfig containerConfig = ContainerConfig.builder()
        .hostConfig(hostConfig)
        .build();

    //noinspection unchecked
    when(asyncInvoker.method(
        anyString(), any(Entity.class), any(Class.class)))
        .thenReturn(Futures.immediateFuture(ContainerCreation.builder().build()));

    dockerClient.createContainer(containerConfig);

    final ArgumentCaptor<String> methodArg = ArgumentCaptor.forClass(String.class);
    final ArgumentCaptor<Entity> entityArg = ArgumentCaptor.forClass(Entity.class);
    final ArgumentCaptor<Class> classArg = ArgumentCaptor.forClass(Class.class);

    //noinspection unchecked
    verify(asyncInvoker, times(1)).method(
        methodArg.capture(), entityArg.capture(), classArg.capture());

    final Entity expectedEntity = Entity.entity(
        containerConfig, new Variant(MediaType.valueOf(APPLICATION_JSON), (String) null, null));

    // Check that we've called the right method on the underlying AsyncInvoker with the right params
    assertThat(methodArg.getValue(), equalTo("POST"));
    assertThat(entityArg.getValue(), equalTo(expectedEntity));
    assertThat(classArg.getValue(), instanceOf(Class.class));
  }

}