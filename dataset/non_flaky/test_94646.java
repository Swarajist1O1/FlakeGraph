class DummyClass_94646 {
  @Test
  public void nonRetryableSSLHandshakeException() throws Exception {
    ConnectionSpecSelector connectionSpecSelector =
        createConnectionSpecSelector(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS);
    SSLSocket socket = createSocketWithEnabledProtocols(TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
    connectionSpecSelector.configureSecureSocket(socket);

    SSLHandshakeException trustIssueException =
        new SSLHandshakeException("Certificate handshake exception");
    trustIssueException.initCause(new CertificateException());
    boolean retry = connectionSpecSelector.connectionFailed(trustIssueException);
    assertFalse(retry);
    socket.close();
  }

}