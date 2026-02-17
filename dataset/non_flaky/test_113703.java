class DummyClass_113703 {
    @Test
    public void serverCanCancelClientStreamImplicitly() {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        svc.setExplicitCancel(false);

        AtomicBoolean requestWasCanceled = new AtomicBoolean(false);
        AtomicBoolean requestDidProduce = new AtomicBoolean(false);

        Flux<NumberProto.Number> request = Flux
                .fromIterable(IntStream.range(0, NUMBER_OF_STREAM_ELEMENTS)::iterator)
                .delayElements(Duration.ofMillis(SEQUENCE_DELAY_MILLIS))
                .map(CancellationPropagationIntegrationTest::protoNum)
                .doOnNext(x -> {
                    requestDidProduce.set(true);
                    System.out.println("Produced: " + x.getNumber(0));
                })
                .doOnCancel(() -> {
                    requestWasCanceled.set(true);
                    System.out.println("Client canceled");
                });

        Mono<NumberProto.Number> observer = request.as(stub::requestPressure)
                .doOnSuccess(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()));

        StepVerifier.create(observer)
                .expectNext(protoNum(9))
                .verifyComplete();

        await().atMost(org.awaitility.Duration.FIVE_HUNDRED_MILLISECONDS).untilTrue(requestWasCanceled);

        assertThat(requestWasCanceled.get()).isTrue();
        assertThat(requestDidProduce.get()).isTrue();
    }

}