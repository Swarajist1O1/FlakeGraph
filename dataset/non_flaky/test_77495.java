class DummyClass_77495 {
    @BeforeEach
    public void clearAllLoggers() {
        //this must be a clear all because the validation runs in other threads
        TestLoggerFactory.clearAll();
    }

}