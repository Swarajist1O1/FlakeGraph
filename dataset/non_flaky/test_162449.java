class DummyClass_162449 {
    @Test
    public void testWaitUntilReadyWithTimeoutAndBadResponseBody() {
        waitUntilReadyAndTimeout(createShellCommand("200 OK", "Bad Response"));
    }

}