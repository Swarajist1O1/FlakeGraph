class DummyClass_113701 {
    @Test
    public void clientCanCancelServerStreamExplicitly() throws InterruptedException {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        AtomicInteger lastNumberConsumed = new AtomicInteger(Integer.MAX_VALUE);
        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());
        Flux<NumberProto.Number> test = Mono.just(Empty.getDefaultInstance()).as(stub::responsePressure)
                .doOnNext(number -> {lastNumberConsumed.set(number.getNumber(0)); System.out.println("C: " + number.getNumber(0));})
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnCancel(() -> System.out.println("Client canceled"));

        Disposable subscription = test.publish().connect();

        Thread.sleep(1000);
        subscription.dispose();
        Thread.sleep(1000);

        // Cancellation may or may not deliver the last generated message due to delays in the gRPC processing thread
        assertThat(Math.abs(lastNumberConsumed.get() - svc.getLastNumberProduced())).isLessThanOrEqualTo(3);
        assertThat(svc.wasCanceled()).isTrue();
    }

}