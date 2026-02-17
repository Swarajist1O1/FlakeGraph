class DummyClass_113792 {
    @Test
    public void serverCanCancelClientStreamImplicitly() {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        RxNumbersGrpc.RxNumbersStub stub = RxNumbersGrpc.newRxStub(serverRule.getChannel());

        svc.setExplicitCancel(false);

        AtomicBoolean requestWasCanceled = new AtomicBoolean(false);
        AtomicBoolean requestDidProduce = new AtomicBoolean(false);

        Flowable<NumberProto.Number> request = Flowable
                .fromIterable(IntStream.range(0, NUMBER_OF_STREAM_ELEMENTS)::iterator)
                .delay(10, TimeUnit.MILLISECONDS)
                .map(CancellationPropagationIntegrationTest::protoNum)
                .doOnNext(x -> {
                    requestDidProduce.set(true);
                    System.out.println("Produced: " + x.getNumber(0));
                })
                .doOnCancel(() -> {
                    requestWasCanceled.set(true);
                    System.out.println("Client canceled");
                });

        TestObserver<NumberProto.Number> observer = request
                .as(stub::requestPressure)
                .doOnSuccess(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .test();

        observer.awaitTerminalEvent(3, TimeUnit.SECONDS);
        observer.assertComplete();
        observer.assertTerminated();

        await().atMost(Duration.FIVE_HUNDRED_MILLISECONDS).untilTrue(requestWasCanceled);

        assertThat(requestWasCanceled.get()).isTrue();
        assertThat(requestDidProduce.get()).isTrue();

        errorRule.verifyNoError();
    }

}