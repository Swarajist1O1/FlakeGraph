class DummyClass_113767 {
    @Test
    public void onCompletedDelegates() {
        ServerCallStreamObserver<Object> obs = mock(ServerCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherServer<Object> pub = new ReactiveStreamObserverPublisherServer<Object>(obs);
        pub.subscribe(sub);

        pub.onCompleted();
        verify(sub).onComplete();
    }

}