class DummyClass_98314 {
  @Test
  public void testHostForIpHttps() {
    final DefaultDockerClient client = DefaultDockerClient.builder()
        .uri("https://192.168.53.103:2375").build();
    assertThat(client.getHost(), equalTo("192.168.53.103"));
  }

}