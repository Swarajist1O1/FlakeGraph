class DummyClass_113766 {
    @Test
    public void onErrorDelegates() {
        ServerCallStreamObserver<Object> obs = mock(ServerCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherServer<Object> pub = new ReactiveStreamObserverPublisherServer<Object>(obs);
        pub.subscribe(sub);

        Throwable obj = new Exception();

        pub.onError(obj);
        verify(sub).onError(obj);
    }

}