class DummyClass_94681 {
  @Test public void invalidClientAuthFails() throws Throwable {
  public OkHttpClient buildClient(HeldCertificate cert, HeldCertificate... chain) {
    SslClient.Builder sslClientBuilder = new SslClient.Builder()
        .addTrustedCertificate(serverRootCa.certificate);

    if (cert != null) {
      sslClientBuilder.certificateChain(cert, chain);
    }

    SslClient sslClient = sslClientBuilder.build();
    return new OkHttpClient.Builder()
        .sslSocketFactory(sslClient.socketFactory, sslClient.trustManager)
        .build();
  }

}