class DummyClass_113735 {
    @Test
    public void oneToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<String> reactorRequest = Mono.just("World");
        Mono<String> reactorResponse = reactorRequest.map(this::toRequest).compose(stub::sayHello).map(this::fromResponse);

        StepVerifier.create(reactorResponse)
                .expectNext("Hello World")
                .verifyComplete();
    }

}