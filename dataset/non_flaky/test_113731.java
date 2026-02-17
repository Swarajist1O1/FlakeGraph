class DummyClass_113731 {
    @Test
    public void oneToMany() throws IOException {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<HelloRequest> req = Mono.just(HelloRequest.newBuilder().setName("reactorjava").build());
        Flux<HelloResponse> resp = req.as(stub::sayHelloRespStream);

        StepVerifier.create(resp.map(HelloResponse::getMessage))
                .expectNext("Hello reactorjava", "Hi reactorjava", "Greetings reactorjava")
                .verifyComplete();
    }

}