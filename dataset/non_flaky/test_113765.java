class DummyClass_113765 {
    @Test
    public void onNextDelegates() {
        ServerCallStreamObserver<Object> obs = mock(ServerCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherServer<Object> pub = new ReactiveStreamObserverPublisherServer<Object>(obs);
        pub.subscribe(sub);

        Object obj = new Object();

        pub.onNext(obj);
        verify(sub).onNext(obj);
    }

}