class DummyClass_113721 {
    @Test
    public void oneToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<HelloRequest> req = Mono.just(HelloRequest.newBuilder().setName("reactorjava").build());
        Mono<HelloResponse> resp = req.compose(stub::sayHello);

        AtomicReference<String> clientThreadName = new AtomicReference<>();

        StepVerifier
                .create(resp
                        .map(HelloResponse::getMessage)
                        .doOnSuccess(x -> clientThreadName.set(Thread.currentThread().getName())))
                .expectNext("Hello reactorjava")
                .verifyComplete();

        assertThat(clientThreadName.get()).isEqualTo("TheGrpcClient");
        assertThat(serverThreadName.get()).isEqualTo("TheGrpcServer");
    }

}