class DummyClass_113774 {
    @Test
    public void statusRuntimeExceptionTriggersHandler() {
        ClientResponseObserver<Object, Object> delegate = mock(ClientResponseObserver.class);
        final AtomicBoolean called = new AtomicBoolean(false);

        CancellableStreamObserver<Object, Object> observer = new CancellableStreamObserver<Object, Object>(delegate, new Runnable() {
            @Override
            public void run() {
                called.set(true);
            }

}