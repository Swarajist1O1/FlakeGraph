class DummyClass_162452 {
    @Test
    public void testWaitUntilReady_Timeout() {
        waitUntilReadyAndTimeout("echo -e \"" + READY_MESSAGE + "\";" +
                "echo -e \"foobar\";" +
                "sleep 300");
    }

}