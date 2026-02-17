class DummyClass_162444 {
    @Test
    public void testWaitUntilReadyWithUnauthorizedWithLambda() {
        waitUntilReadyAndSucceed(startContainerWithCommand(createShellCommand("401 UNAUTHORIZED", GOOD_RESPONSE_BODY),
            createHttpWaitStrategy(ready)
                .forStatusCodeMatching(it -> it >= 200 && it < 300 || it == 401)
        ));
    }

}