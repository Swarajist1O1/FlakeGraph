class DummyClass_162451 {
    @Test
    public void testWaitUntilReady_Success() {
        waitUntilReadyAndSucceed("echo -e \"" + READY_MESSAGE + "\";" +
                "echo -e \"foobar\";" +
                "echo -e \"" + READY_MESSAGE + "\";" +
                "sleep 300");
    }

}