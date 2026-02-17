class DummyClass_113712 {
    @Test
    public void serverToClientBackpressure() {
        serverRule.getServiceRegistry().addService(new TestService());

        ReactorNumbersGrpc.ReactorNumbersStub stub = ReactorNumbersGrpc.newReactorStub(serverRule.getChannel());

        Mono<Empty> reactorRequest = Mono.just(Empty.getDefaultInstance());

        Flux<NumberProto.Number> reactorResponse = reactorRequest.as(stub::responsePressure)
                .doOnNext(n -> System.out.println(n.getNumber(0) + "  <--"))
                .doOnNext(n -> waitIfValuesAreEqual(n.getNumber(0), 3));

        StepVerifier.create(reactorResponse)
                .expectNextCount(NUMBER_OF_STREAM_ELEMENTS)
                .expectComplete()
                .verify(Duration.ofSeconds(5));

        assertThat(numberOfWaits.get()).isEqualTo(1);
    }

}