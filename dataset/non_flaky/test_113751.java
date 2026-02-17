class DummyClass_113751 {
    @Test
    public void rxConsumerIsSet() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        ReactorConsumerStreamObserver rxObs = new ReactorConsumerStreamObserver();

        rxObs.beforeStart(obs);

        assertThat(rxObs.getRxConsumer()).isNotNull();
    }

}