class DummyClass_113785 {
    @Test
    public void onNextStopsPump() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        when(obs.isReady()).thenReturn(false);

        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);

        handler.onSubscribe(sub);

        Object obj = new Object();
        handler.onNext(obj);

        verify(obs).onNext(obj);
        verify(sub, never()).request(1);
    }

}