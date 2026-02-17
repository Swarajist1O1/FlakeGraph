class DummyClass_113772 {
    @Test
    public void requestDelegates() {
        ClientCallStreamObserver<Object> obs = mock(ClientCallStreamObserver.class);
        Subscriber<Object> sub = mock(Subscriber.class);

        final AtomicReference<Subscription> subscription = new AtomicReference<Subscription>();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) {
                subscription.set((Subscription) invocationOnMock.getArguments()[0]);
                return null;
            }

}