class DummyClass_77481 {
@TestLogging(value = "org.opensearch.transport.TransportLogger:trace", reason = "to ensure we log network events on TRACE level")
    public void setUp() throws Exception {
        super.setUp();
        appender = new MockLogAppender();
        Loggers.addAppender(LogManager.getLogger(TransportLogger.class), appender);
        appender.start();
    }

}