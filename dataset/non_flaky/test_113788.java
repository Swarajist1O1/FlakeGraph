class DummyClass_113788 {
    @Test
    public void exceptionInOnCompleteCancelsUpstreamSubscription() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        doThrow(new IllegalStateException("won't be propagated to handler caller")).when(obs).onCompleted();
        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);
        handler.onSubscribe(sub);
        
        handler.onComplete();
        verify(obs).cancel(anyString(), any(Throwable.class));
        verify(obs).onError(any(Throwable.class));
    }

}