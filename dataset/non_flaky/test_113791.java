class DummyClass_113791 {
    @Test
    public void clientCanCancelServerStreamImplicitly() throws InterruptedException {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        RxNumbersGrpc.RxNumbersStub stub = RxNumbersGrpc.newRxStub(serverRule.getChannel());
        TestSubscriber<NumberProto.Number> subscription =  Single.just(Empty.getDefaultInstance())
                .as(stub::responsePressure)
                .doOnNext(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnCancel(() -> System.out.println("Client canceled"))
                .take(10)
                .test();

        // Consume some work
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        subscription.dispose();

        subscription.awaitTerminalEvent(3, TimeUnit.SECONDS);
        subscription.assertValueCount(10);
        subscription.assertTerminated();
        assertThat(svc.wasCanceled()).isTrue();

        errorRule.verifyNoError();
    }

}