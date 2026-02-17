class DummyClass_94647 {
  @Test
  public void retryableSSLHandshakeException() throws Exception {
    ConnectionSpecSelector connectionSpecSelector =
        createConnectionSpecSelector(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS);
    SSLSocket socket = createSocketWithEnabledProtocols(TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
    connectionSpecSelector.configureSecureSocket(socket);

    boolean retry = connectionSpecSelector.connectionFailed(RETRYABLE_EXCEPTION);
    assertTrue(retry);
    socket.close();
  }

}