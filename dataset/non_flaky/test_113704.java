class DummyClass_113704 {
    @Test
    public void serverCanCancelClientStreamExplicitly() {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        svc.setExplicitCancel(true);

        AtomicBoolean requestWasCanceled = new AtomicBoolean(false);
        AtomicBoolean requestDidProduce = new AtomicBoolean(false);

        Flux<NumberProto.Number> request = Flux
                .fromIterable(IntStream.range(0, NUMBER_OF_STREAM_ELEMENTS)::iterator)
                .delayElements(Duration.ofMillis(SEQUENCE_DELAY_MILLIS))
                .map(CancellationPropagationIntegrationTest::protoNum)
                .doOnNext(n -> {
                    requestDidProduce.set(true);
                    System.out.println("P: " + n.getNumber(0));
                })
                .doOnCancel(() -> {
                    requestWasCanceled.set(true);
                    System.out.println("Client canceled");
                });

        Mono<NumberProto.Number> observer = request.as(stub::requestPressure)
                .doOnSuccess(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()));

        StepVerifier.create(observer)
                .expectNext(protoNum(-1))
                .verifyComplete();

        await().atMost(org.awaitility.Duration.FIVE_HUNDRED_MILLISECONDS).untilTrue(requestWasCanceled);

        assertThat(requestWasCanceled.get()).isTrue();
        assertThat(requestDidProduce.get()).isTrue();
    }

}