class DummyClass_98313 {
  @Test
  public void testHostForFqdnHttps() {
    final DefaultDockerClient client = DefaultDockerClient.builder()
        .uri("https://perdu.com:2375").build();
    assertThat(client.getHost(), equalTo("perdu.com"));
  }

}