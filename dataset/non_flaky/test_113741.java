class DummyClass_113741 {
    @Test
    public void ServerAcceptsContext() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);

        StepVerifier.create(worldReq.compose(stub::sayHello).map(HelloResponse::getMessage))
                .expectNext("Hello World")
                .verifyComplete();
        assertThat(svc.getReceivedCtxValue()).isEqualTo("ServerAcceptsContext");
    }

}