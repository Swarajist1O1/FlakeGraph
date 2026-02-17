class DummyClass_113770 {
    @Test
    public void onErrorDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        ReactiveStreamObserverPublisherClient<Object> pub = new ReactiveStreamObserverPublisherClient<Object>(obs);
        pub.subscribe(sub);

        Throwable obj = new Exception();

        pub.onError(obj);
        verify(sub).onError(obj);
    }

}