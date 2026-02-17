class DummyClass_113753 {
    @Test
    public void onErrorDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        ReactorConsumerStreamObserver rxObs = new ReactorConsumerStreamObserver();
        Subscriber<Object> sub = mock(Subscriber.class);

        rxObs.beforeStart(obs);
        rxObs.getRxConsumer().subscribe(sub);

        Throwable obj = new Exception("test error");
        StepVerifier.create(rxObs.getRxConsumer())
                .then(() -> rxObs.onError(obj))
                .expectErrorMessage("test error")
                .verify(Duration.ofSeconds(3));
    }

}