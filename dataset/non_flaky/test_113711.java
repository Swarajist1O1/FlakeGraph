class DummyClass_113711 {
    @Test
    public void clientToServerBackpressure() {
        serverRule.getServiceRegistry().addService(new TestService());

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        Flux<NumberProto.Number> reactorRequest = Flux
                .fromIterable(IntStream.range(0, NUMBER_OF_STREAM_ELEMENTS)::iterator)
                .doOnNext(i -> System.out.println(i + " --> "))
                .doOnNext(i -> updateNumberOfWaits(lastValueTime, numberOfWaits))
                .map(BackpressureIntegrationTest::protoNum);

        Mono<NumberProto.Number> reactorResponse = reactorRequest.as(stub::requestPressure);

        StepVerifier.create(reactorResponse)
                .expectNextMatches(v -> v.getNumber(0) == NUMBER_OF_STREAM_ELEMENTS - 1)
                .expectComplete()
                .verify(Duration.ofSeconds(5));

        assertThat(numberOfWaits.get()).isEqualTo(1);
    }

}