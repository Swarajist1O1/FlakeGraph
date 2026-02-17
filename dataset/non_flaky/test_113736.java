class DummyClass_113736 {
    @Test
    public void oneToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<String> reactorRequest = Mono.just("World");
        Flux<String> reactorResponse = reactorRequest.map(this::toRequest).as(stub::sayHelloRespStream).map(this::fromResponse);

        StepVerifier.create(reactorResponse)
                .expectNext("Hello World", "Hi World", "Greetings World")
                .verifyComplete();
    }

}