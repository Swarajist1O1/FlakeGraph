class DummyClass_113740 {
    @Test
    public void ClientGetsContext() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);

        Mono<HelloResponse> test = worldReq.compose(stub::sayHello)
                .doOnSuccess(resp -> {
                    Context ctx = Context.current();
                    assertThat(ctxKey.get(ctx)).isEqualTo("ClientGetsContext");
                });

        StepVerifier.create(test.map(HelloResponse::getMessage))
                .expectNext("Hello World")
                .verifyComplete();
    }

}