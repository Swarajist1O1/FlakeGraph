class DummyClass_113716 {
    @Test
    public void serverErrorSignalsUpstreamCancellationBidi() {
        serverRule.getServiceRegistry().addService(new ExplodeAfterFiveService());
        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        AtomicBoolean upstreamCancel = new AtomicBoolean(false);

        Flux<NumberProto.Number> subscriber = Flux.range(0, Integer.MAX_VALUE)
                .map(this::protoNum)
                .doOnCancel(() -> upstreamCancel.set(true))
                .compose(stub::twoWayPressure)
                .doOnNext(i -> System.out.println(i.getNumber(0)));

        StepVerifier.create(subscriber)
                .verifyError(StatusRuntimeException.class);
        assertThat(upstreamCancel.get()).isTrue();
    }

}