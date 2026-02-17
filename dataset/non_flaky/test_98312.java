class DummyClass_98312 {
  @Test
  public void testHostForLocalHttps() {
    final DefaultDockerClient client = DefaultDockerClient.builder()
        .uri("https://localhost:2375").build();
    assertThat(client.getHost(), equalTo("localhost"));
  }

}