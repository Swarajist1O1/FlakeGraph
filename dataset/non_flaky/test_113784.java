class DummyClass_113784 {
    @Test
    public void onNextKeepsPumpRunning() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        when(obs.isReady()).thenReturn(true);

        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);

        handler.onSubscribe(sub);

        Object obj = new Object();
        handler.onNext(obj);

        verify(obs).onNext(obj);
        verify(sub).request(1);
    }

}