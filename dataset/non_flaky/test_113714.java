class DummyClass_113714 {
    @Test
    public void bidiRequestBackpressure() {
        serverRule.getServiceRegistry().addService(new TestService());

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        Flux<NumberProto.Number> reactorRequest = Flux
                .fromIterable(IntStream.range(0, NUMBER_OF_STREAM_ELEMENTS)::iterator)
                .doOnNext(i -> System.out.println(i + " --> "))
                .doOnNext(i -> updateNumberOfWaits(lastValueTime, numberOfWaits))
                .map(BackpressureIntegrationTest::protoNum);

        Flux<NumberProto.Number> reactorResponse = reactorRequest.compose(stub::twoWayRequestPressure);

        StepVerifier.create(reactorResponse)
                .expectNextMatches(v -> v.getNumber(0) == NUMBER_OF_STREAM_ELEMENTS - 1)
                .expectComplete()
                .verify(Duration.ofSeconds(5));

        assertThat(numberOfWaits.get()).isEqualTo(1);
    }

}