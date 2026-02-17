class DummyClass_113786 {
    @Test
    public void exceptionInOnNextCancelsUpstreamSubscription() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        doThrow(new IllegalStateException("won't be propagated to handler caller")).when(obs).onNext(any());
        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);
        handler.onSubscribe(sub);
        
        handler.onNext(new Object());
        verify(obs).cancel(anyString(), any(Throwable.class));
        verify(obs).onError(any(Throwable.class));
    }

}