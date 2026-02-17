class DummyClass_113739 {
    @Test
    public void ClientSendsContext() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Context.current()
                .withValue(ctxKey, "ClientSendsContext")
                .run(() -> StepVerifier.create(worldReq.compose(stub::sayHello).map(HelloResponse::getMessage))
                        .expectNext("Hello World")
                        .verifyComplete());

        assertThat(clientInterceptor.getSendMessageCtxValue()).isEqualTo("ClientSendsContext");
    }

}