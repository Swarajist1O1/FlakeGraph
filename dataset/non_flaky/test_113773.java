class DummyClass_113773 {
    @Test
    public void statusExceptionTriggersHandler() {
        ClientResponseObserver<Object, Object> delegate = mock(ClientResponseObserver.class);
        final AtomicBoolean called = new AtomicBoolean(false);

        CancellableStreamObserver<Object, Object> observer = new CancellableStreamObserver<Object, Object>(delegate, new Runnable() {
            @Override
            public void run() {
                called.set(true);
            }

}