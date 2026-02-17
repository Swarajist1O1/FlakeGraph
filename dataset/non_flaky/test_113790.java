class DummyClass_113790 {
    @Test
    public void clientCanCancelServerStreamExplicitly() throws InterruptedException {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        RxNumbersGrpc.RxNumbersStub stub = RxNumbersGrpc.newRxStub(serverRule.getChannel());
        TestSubscriber<NumberProto.Number> subscription = Single.just(Empty.getDefaultInstance())
                .as(stub::responsePressure)
                .doOnNext(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnCancel(() -> System.out.println("Client canceled"))
                .test();

        Thread.sleep(250);
        subscription.dispose();
        Thread.sleep(250);

        subscription.awaitTerminalEvent(3, TimeUnit.SECONDS);
        // Cancellation may or may not deliver the last generated message due to delays in the gRPC processing thread
        assertThat(Math.abs(subscription.valueCount() - svc.getLastNumberProduced())).isLessThanOrEqualTo(3);
        assertThat(svc.wasCanceled()).isTrue();

        errorRule.verifyNoError();
    }

}