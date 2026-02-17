class DummyClass_98317 {
  @Test
  public void testMultipleHeaders() throws Exception {
    final Map<String, Object> headers = Maps.newHashMap();
    headers.put("int", 1);
    headers.put("string", "2");
    headers.put("list", Lists.newArrayList("a", "b", "c"));

    for (final Map.Entry<String, Object> entry : headers.entrySet()) {
      builder.header(entry.getKey(), entry.getValue());
    }

    final DefaultDockerClient dockerClient = new DefaultDockerClient(
        builder, clientBuilderSupplier);
    dockerClient.info();

    final ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
    final ArgumentCaptor<String> valueCaptor = ArgumentCaptor.forClass(String.class);
    verify(builderMock, times(headers.size())).header(nameCaptor.capture(), valueCaptor.capture());

    int idx = 0;
    for (final Map.Entry<String, Object> entry : headers.entrySet()) {
      Assert.assertEquals(entry.getKey(), nameCaptor.getAllValues().get(idx));
      Assert.assertEquals(entry.getValue(), valueCaptor.getAllValues().get(idx));
      ++idx;
    }
  }

}