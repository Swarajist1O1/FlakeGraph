class DummyClass_113713 {
    @Test
    public void bidiResponseBackpressure() {
        serverRule.getServiceRegistry().addService(new TestService());

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        Flux<NumberProto.Number> reactorRequest = Flux.empty();

        Flux<NumberProto.Number> reactorResponse = reactorRequest.compose(stub::twoWayResponsePressure)
                .doOnNext(n -> System.out.println(n.getNumber(0) + "  <--"))
                .doOnNext(n -> waitIfValuesAreEqual(n.getNumber(0), 3));

        StepVerifier.create(reactorResponse)
                .expectNextCount(NUMBER_OF_STREAM_ELEMENTS)
                .expectComplete()
                .verify(Duration.ofSeconds(5));

        assertThat(numberOfWaits.get()).isEqualTo(1);
    }

}