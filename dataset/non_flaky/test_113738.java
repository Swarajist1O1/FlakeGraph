class DummyClass_113738 {
    @Test
    public void manyToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<String> reactorRequest = Flux.just("A", "B", "C", "D");
        Flux<String> reactorResponse = reactorRequest.map(this::toRequest).compose(stub::sayHelloBothStream).map(this::fromResponse);

        StepVerifier.create(reactorResponse)
                .expectNext("Hello A and B", "Hello C and D")
                .verifyComplete();
    }

}