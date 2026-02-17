class DummyClass_98327 {
  @Test
  public void testFromUnixSocket() throws Exception {
    final String unixSocket = "unix:///var/run/docker.sock";
    final String certPath = "/path/to/cert";
    final URI unixSocketUri = new URI(unixSocket);

    final DockerHost dockerHost = DockerHost.from(unixSocket, certPath);
    assertThat(dockerHost.host(), equalTo(unixSocket));
    assertThat(dockerHost.uri(), equalTo(unixSocketUri));
    assertThat(dockerHost.bindUri(), equalTo(unixSocketUri));
    assertThat(dockerHost.port(), equalTo(0));
    assertThat(dockerHost.address(), equalTo("localhost"));
    assertThat(dockerHost.dockerCertPath(), equalTo(certPath));
  }

}