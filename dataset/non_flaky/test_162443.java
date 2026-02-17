class DummyClass_162443 {
    @Test
    public void testWaitUntilReadyWithSuccess() {
        waitUntilReadyAndSucceed(createShellCommand("200 OK", GOOD_RESPONSE_BODY));
    }

}