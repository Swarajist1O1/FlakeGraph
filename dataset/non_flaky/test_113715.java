class DummyClass_113715 {
    @Test
    public void serverErrorSignalsUpstreamCancellationManyToOne() {
        serverRule.getServiceRegistry().addService(new ExplodeAfterFiveService());
        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        AtomicBoolean upstreamCancel = new AtomicBoolean(false);

        Mono<NumberProto.Number> observer = Flux.range(0, Integer.MAX_VALUE)
                .map(this::protoNum)
                .doOnCancel(() -> upstreamCancel.set(true))
                .as(stub::requestPressure)
                .doOnError(System.out::println)
                .doOnSuccess(i -> System.out.println(i.getNumber(0)));

        StepVerifier.create(observer)
                .verifyError(StatusRuntimeException.class);

        assertThat(upstreamCancel.get()).isTrue();
    }

}