class DummyClass_113737 {
    @Test
    public void manyToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<String> reactorRequest = Flux.just("A", "B", "C");
        Mono<String> reactorResponse = reactorRequest.map(this::toRequest).as(stub::sayHelloReqStream).map(this::fromResponse);

        StepVerifier.create(reactorResponse)
                .expectNext("Hello A and B and C")
                .verifyComplete();
    }

}