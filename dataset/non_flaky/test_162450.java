class DummyClass_162450 {
    @Test
    public void testWaitUntilReadyWithSpecificPort() {
        waitUntilReadyAndSucceed(startContainerWithCommand(
            createShellCommand("200 OK", GOOD_RESPONSE_BODY, 9090),
            createHttpWaitStrategy(ready)
                .forPort(9090),
            7070, 8080, 9090
        ));
    }

}