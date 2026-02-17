class DummyClass_113787 {
    @Test
    public void exceptionInOnOnErrorCancelsUpstreamSubscription() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        doThrow(new IllegalStateException("won't be propagated to handler caller")).when(obs).onError(any(Throwable.class));
        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub = mock(Subscription.class);
        handler.onSubscribe(sub);
        
        handler.onError(new RuntimeException());
        verify(obs).cancel(anyString(), any(Throwable.class));
    }

}