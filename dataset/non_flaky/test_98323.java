class DummyClass_98323 {
  @Test
  public void testDefaultPort() throws Exception {
    assertThat(DockerHost.defaultPort(), equalTo(2375));
  }

}