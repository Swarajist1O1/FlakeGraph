class DummyClass_113749 {
    @Test
    public void subscribeOnlyOnceLifterErrorsWhenMultipleSubscribe() throws Exception {
        SubscribeOnlyOnceLifter<Object> op = new SubscribeOnlyOnceLifter<>();
        CoreSubscriber<Object> innerSub = mock(CoreSubscriber.class);
        Subscription subscription = mock(Subscription.class);

        CoreSubscriber<Object> outerSub = op.apply(null, innerSub);

        outerSub.onSubscribe(subscription);
        assertThatThrownBy(() -> outerSub.onSubscribe(subscription))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("cannot directly subscribe to a gRPC service multiple times");

        verify(innerSub, times(1)).onSubscribe(subscription);
    }

}