class DummyClass_113768 {
    @Test
    public void requestDelegates() {
        ServerCallStreamObserver<Object> obs = mock(ServerCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        final AtomicReference<Subscription> subscription = new AtomicReference<Subscription>();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) {
                subscription.set((Subscription) invocationOnMock.getArguments()[0]);
                return null;
            }

}