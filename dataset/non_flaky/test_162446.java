class DummyClass_162446 {
    @Test
    public void testWaitUntilReadyWithManyStatusCodesAndLambda() {
        waitUntilReadyAndSucceed(startContainerWithCommand(createShellCommand("401 UNAUTHORIZED", GOOD_RESPONSE_BODY),
            createHttpWaitStrategy(ready)
                .forStatusCode(300)
                .forStatusCode(500)
                .forStatusCodeMatching(it -> it == 401)
        ));
    }

}