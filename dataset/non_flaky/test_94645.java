class DummyClass_94645 {
  @Test
  public void nonRetryableIOException() throws Exception {
    ConnectionSpecSelector connectionSpecSelector =
        createConnectionSpecSelector(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS);
    SSLSocket socket = createSocketWithEnabledProtocols(TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
    connectionSpecSelector.configureSecureSocket(socket);

    boolean retry = connectionSpecSelector.connectionFailed(
        new IOException("Non-handshake exception"));
    assertFalse(retry);
    socket.close();
  }

}