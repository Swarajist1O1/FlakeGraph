class DummyClass_113730 {
    @Test
    public void oneToOne() throws IOException {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<HelloRequest> req = Mono.just(HelloRequest.newBuilder().setName("reactorjava").build());
        Mono<HelloResponse> resp = req.compose(stub::sayHello);

        StepVerifier.create(resp.map(HelloResponse::getMessage))
                .expectNext("Hello reactorjava")
                .verifyComplete();
    }

}