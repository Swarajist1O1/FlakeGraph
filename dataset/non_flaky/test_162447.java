class DummyClass_162447 {
    @Test
    public void testWaitUntilReadyWithTimeoutAndWithManyStatusCodesAndLambda() {
        waitUntilReadyAndTimeout(startContainerWithCommand(createShellCommand("401 UNAUTHORIZED", GOOD_RESPONSE_BODY),
            createHttpWaitStrategy(ready)
                .forStatusCode(300)
                .forStatusCodeMatching(it -> it == 500)
        ));
    }

}