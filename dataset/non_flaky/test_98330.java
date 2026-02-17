class DummyClass_98330 {
  @Test
  public void testFromEnv() throws Exception {
    when(systemDelegate.getProperty("os.name")).thenReturn("linux");
    DockerHost.setSystemDelegate(systemDelegate);

    final String dockerHostEnvVar = DockerHost.defaultDockerEndpoint();
    final boolean isUnixSocket = dockerHostEnvVar.startsWith("unix://");
    final URI dockerHostUri = new URI(dockerHostEnvVar);

    final String dockerHostAndPort;
    final URI dockerHostHttpUri;
    final URI dockerTcpUri;
    final int dockerHostPort;
    final String dockerHostHost;
    if (isUnixSocket) {
      dockerHostAndPort = dockerHostEnvVar;
      dockerHostHttpUri = dockerHostUri;
      dockerTcpUri = dockerHostUri;
      dockerHostPort = 0;
      dockerHostHost = "localhost";
    } else {
      dockerHostAndPort = dockerHostUri.getHost() + ":" + dockerHostUri.getPort();
      dockerHostHttpUri = new URI("http://" + dockerHostAndPort);
      dockerTcpUri = new URI("tcp://" + dockerHostAndPort);
      dockerHostPort = dockerHostUri.getPort();
      dockerHostHost = dockerHostUri.getHost();
    }

    final DockerHost dockerHost = DockerHost.fromEnv();
    assertThat(dockerHost.host(), equalTo(dockerHostAndPort));
    assertThat(dockerHost.uri(), equalTo(dockerHostHttpUri));
    assertThat(dockerHost.bindUri(), equalTo(dockerTcpUri));
    assertThat(dockerHost.port(), equalTo(dockerHostPort));
    assertThat(dockerHost.address(), equalTo(dockerHostHost));
    assertThat(dockerHost.dockerCertPath(), nullValue());
  }

}