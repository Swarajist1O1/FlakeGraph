class DummyClass_113722 {
    @Test
    public void manyToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloRequest> req = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build(),
                HelloRequest.newBuilder().setName("d").build(),
                HelloRequest.newBuilder().setName("e").build());

        Flux<HelloResponse> resp = req.compose(stub::sayHelloBothStream);

        AtomicReference<String> clientThreadName = new AtomicReference<>();

        StepVerifier
                .create(resp
                        .map(HelloResponse::getMessage)
                        .doOnNext(x -> clientThreadName.set(Thread.currentThread().getName())))
                .expectNext("Hello a and b", "Hello c and d", "Hello e")
                .verifyComplete();

        assertThat(clientThreadName.get()).isEqualTo("TheGrpcClient");
        assertThat(serverThreadName.get()).isEqualTo("TheGrpcServer");
    }

}