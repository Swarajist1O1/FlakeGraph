class DummyClass_113783 {
    @Test
    public void runPrimesThePump() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        when(obs.isReady()).thenReturn(true);
        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);

        handler.onSubscribe(sub);

        handler.run();
        verify(sub).request(1);
    }

}