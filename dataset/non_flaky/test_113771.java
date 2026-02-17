class DummyClass_113771 {
    @Test
    public void onCompletedDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherClient<Object> pub = new ReactiveStreamObserverPublisherClient<Object>(obs);
        pub.subscribe(sub);

        pub.onCompleted();
        verify(sub).onComplete();
    }

}