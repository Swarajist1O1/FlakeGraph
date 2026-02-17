class DummyClass_113734 {
    @Test
    public void fourKindsOfRequestAtOnce() throws Exception {
        StepVerifier.setDefaultTimeout(Duration.ofSeconds(3));

        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);

        // == MAKE REQUESTS ==
        // One to One
        Mono<HelloRequest> req1 = Mono.just(HelloRequest.newBuilder().setName("reactorjava").build());
        Mono<HelloResponse> resp1 = req1.compose(stub::sayHello);

        // One to Many
        Mono<HelloRequest> req2 = Mono.just(HelloRequest.newBuilder().setName("reactorjava").build());
        Flux<HelloResponse> resp2 = req2.as(stub::sayHelloRespStream);

        // Many to One
        Flux<HelloRequest> req3 = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build());

        Mono<HelloResponse> resp3 = req3.as(stub::sayHelloReqStream);

        // Many to Many
        Flux<HelloRequest> req4 = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build(),
                HelloRequest.newBuilder().setName("d").build(),
                HelloRequest.newBuilder().setName("e").build());

        Flux<HelloResponse> resp4 = req4.compose(stub::sayHelloBothStream);

        // == VERIFY RESPONSES ==
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

        // Run all four verifications in parallel
        try {
            // One to One
            ListenableFuture<Boolean> oneToOne = executorService.submit(() -> {
                StepVerifier.create(resp1.map(HelloResponse::getMessage))
                        .expectNext("Hello reactorjava")
                        .verifyComplete();
                return true;
            });

            // One to Many
            ListenableFuture<Boolean> oneToMany = executorService.submit(() -> {
                StepVerifier.create(resp2.map(HelloResponse::getMessage))
                        .expectNext("Hello reactorjava", "Hi reactorjava", "Greetings reactorjava")
                        .verifyComplete();
                return true;
            });

            // Many to One
            ListenableFuture<Boolean> manyToOne = executorService.submit(() -> {
                StepVerifier.create(resp3.map(HelloResponse::getMessage))
                        .expectNext("Hello a and b and c")
                        .verifyComplete();
                return true;
            });

            // Many to Many
            ListenableFuture<Boolean> manyToMany = executorService.submit(() -> {
                StepVerifier.create(resp4.map(HelloResponse::getMessage))
                        .expectNext("Hello a and b", "Hello c and d", "Hello e")
                        .verifyComplete();
                return true;
            });

            ListenableFuture<List<Boolean>> allFutures = Futures.allAsList(Lists.newArrayList(oneToOne, oneToMany, manyToOne, manyToMany));
            // Block for response
            List<Boolean> results = allFutures.get(3, TimeUnit.SECONDS);
            assertThat(results).containsExactly(true, true, true, true);

        } finally {
            executorService.shutdown();
        }
    }

}