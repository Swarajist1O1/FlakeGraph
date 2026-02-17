class DummyClass_113748 {
@Test(timeOut = 3000)
    public Subscriber<Message> createSubscriber(WhiteboxSubscriberProbe<Message> probe) {
        return new ReactivePublisherBackpressureOnReadyHandlerClient<Message>(new StubServerCallStreamObserver()) {
            @Override
            public void onSubscribe(final Subscription s) {
                super.onSubscribe(s);

                // register a successful Subscription, and create a Puppet,
                // for the WhiteboxVerification to be able to drive its tests:
                probe.registerOnSubscribe(new SubscriberPuppet() {

                    @Override
                    public void triggerRequest(long elements) {
                        s.request(elements);
                    }

}