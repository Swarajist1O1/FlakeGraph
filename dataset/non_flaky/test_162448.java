class DummyClass_162448 {
    @Test
    public void testWaitUntilReadyWithTimeout() {
        waitUntilReadyAndTimeout(createShellCommand("400 Bad Request", GOOD_RESPONSE_BODY));
    }

}