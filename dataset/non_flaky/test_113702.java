class DummyClass_113702 {
    @Test
    public void clientCanCancelServerStreamImplicitly() throws InterruptedException {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());
        Flux<NumberProto.Number> test = Mono.just(Empty.getDefaultInstance()).as(stub::responsePressure)
                .doOnNext(number -> System.out.println(number.getNumber(0)))
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnCancel(() -> System.out.println("Client canceled"))
                .take(10);

        Disposable subscription = test.publish().connect();

        Thread.sleep(1000);

        assertThat(svc.wasCanceled()).isTrue();
    }

}