class DummyClass_162445 {
    @Test
    public void testWaitUntilReadyWithManyStatusCodes() {
        waitUntilReadyAndSucceed(startContainerWithCommand(createShellCommand("401 UNAUTHORIZED", GOOD_RESPONSE_BODY),
            createHttpWaitStrategy(ready)
                .forStatusCode(300)
                .forStatusCode(401)
                .forStatusCode(500)
        ));
    }

}