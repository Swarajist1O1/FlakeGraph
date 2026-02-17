class DummyClass_91429 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG," +
    public void setUp() throws Exception {
        super.setUp();
        server = EmailServer.localhost(logger);
    }

}