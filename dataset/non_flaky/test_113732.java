class DummyClass_113732 {
    @Test
    public void manyToOne() throws Exception {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloRequest> req = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build());

        Mono<HelloResponse> resp = req.as(stub::sayHelloReqStream);

        StepVerifier.create(resp.map(HelloResponse::getMessage))
                .expectNext("Hello a and b and c")
                .verifyComplete();
    }

}