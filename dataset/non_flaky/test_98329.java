class DummyClass_98329 {
  @Test
  public void testFromTcpSocketWithCert() throws Exception {
    final String tcpSocket = "tcp://127.0.0.1:2375";
    final String certPath = "/path/to/cert";

    final DockerHost dockerHost = DockerHost.from(tcpSocket, certPath);
    assertThat(dockerHost.host(), equalTo("127.0.0.1:2375"));
    assertThat(dockerHost.uri(), equalTo(new URI("https://127.0.0.1:2375")));
    assertThat(dockerHost.bindUri(), equalTo(new URI(tcpSocket)));
    assertThat(dockerHost.port(), equalTo(2375));
    assertThat(dockerHost.address(), equalTo("127.0.0.1"));
    assertThat(dockerHost.dockerCertPath(), equalTo(certPath));
  }

}