class DummyClass_113752 {
    @Test
    public void onNextDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        ReactorConsumerStreamObserver rxObs = new ReactorConsumerStreamObserver();
        Subscriber<Object> sub = mock(Subscriber.class);

        rxObs.beforeStart(obs);
        rxObs.getRxConsumer().subscribe(sub);

        Object obj = new Object();
        StepVerifier.create(rxObs.getRxConsumer())
                .then(() -> rxObs.onNext(obj))
                .expectNext(obj)
                .then(rxObs::onCompleted)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

}