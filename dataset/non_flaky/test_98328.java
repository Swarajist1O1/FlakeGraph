class DummyClass_98328 {
  @Test
  public void testFromTcpSocketNoCert() throws Exception {
    final String tcpSocket = "tcp://127.0.0.1:2375";
    final DockerHost dockerHost = DockerHost.from(tcpSocket, null);

    assertThat(dockerHost.host(), equalTo("127.0.0.1:2375"));
    assertThat(dockerHost.uri(), equalTo(new URI("http://127.0.0.1:2375")));
    assertThat(dockerHost.bindUri(), equalTo(new URI(tcpSocket)));
    assertThat(dockerHost.port(), equalTo(2375));
    assertThat(dockerHost.address(), equalTo("127.0.0.1"));
    assertThat(dockerHost.dockerCertPath(), nullValue());
  }

}