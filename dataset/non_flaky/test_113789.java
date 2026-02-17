class DummyClass_113789 {
    @Test
    public void onSubscribeCancelsSecondSubscription() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        ReactivePublisherBackpressureOnReadyHandlerClient<Object> handler = new ReactivePublisherBackpressureOnReadyHandlerClient<Object>(obs);
        Subscription sub1 = mock(Subscription.class);
        Subscription sub2 = mock(Subscription.class);

        handler.onSubscribe(sub1);
        handler.onSubscribe(sub2);
        
        verify(sub2).cancel();
    }

}