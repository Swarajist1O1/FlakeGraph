class DummyClass_113777 {
    @Test
    public void requestOneSupplyOneDoesntRequestAnother() {
        int chunkSize = 16;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        downstreamSubscriber.upstreamSubscription.request(1);
        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(chunkSize);
    }

}