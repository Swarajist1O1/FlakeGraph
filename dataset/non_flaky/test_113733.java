class DummyClass_113733 {
    @Test
    public void manyToMany() throws Exception {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloRequest> req = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build(),
                HelloRequest.newBuilder().setName("d").build(),
                HelloRequest.newBuilder().setName("e").build());

        Flux<HelloResponse> resp = req.compose(stub::sayHelloBothStream);

        StepVerifier.create(resp.map(HelloResponse::getMessage))
                .expectNext("Hello a and b", "Hello c and d", "Hello e")
                .verifyComplete();
    }

}