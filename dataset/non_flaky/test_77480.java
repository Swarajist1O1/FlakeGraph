class DummyClass_77480 {
    @TestLogging(reason = "testing logging", value = "org.opensearch.transport.TcpTransport:DEBUG")
    public void testExceptionHandling() throws IllegalAccessException {
        testExceptionHandling(false, new OpenSearchException("simulated"), true,
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.ERROR, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.WARN, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.INFO, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.DEBUG, "*"));
        testExceptionHandling(new OpenSearchException("simulated"),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.WARN, "exception caught on transport layer [*], closing connection"));
        testExceptionHandling(new ClosedChannelException(),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.DEBUG, "close connection exception caught on transport layer [*], disconnecting from relevant node"));
        testExceptionHandling(new OpenSearchException("Connection reset"),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.DEBUG, "close connection exception caught on transport layer [*], disconnecting from relevant node"));
        testExceptionHandling(new BindException(),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.DEBUG, "bind exception caught on transport layer [*]"));
        testExceptionHandling(new CancelledKeyException(),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.DEBUG, "cancelled key exception caught on transport layer [*], disconnecting from relevant node"));
        testExceptionHandling(true, new TcpTransport.HttpRequestOnTransportException("test"), false,
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.ERROR, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.WARN, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.INFO, "*"),
            new MockLogAppender.UnseenEventExpectation("message", "org.opensearch.transport.TcpTransport", Level.DEBUG, "*"));
        testExceptionHandling(new StreamCorruptedException("simulated"),
            new MockLogAppender.SeenEventExpectation("message", "org.opensearch.transport.TcpTransport",
                Level.WARN, "simulated, [*], closing connection"));
    }

}