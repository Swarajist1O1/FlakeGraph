class DummyClass_113769 {
    @Test
    public void onNextDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherClient<Object> pub = new ReactiveStreamObserverPublisherClient<Object>(obs);
        pub.subscribe(sub);

        Object obj = new Object();

        pub.onNext(obj);
        verify(sub).onNext(obj);
    }

}