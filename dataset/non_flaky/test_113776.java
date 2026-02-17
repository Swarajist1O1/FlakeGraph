class DummyClass_113776 {
    @Test
    public void requestOneGetsAChunk() {
        int chunkSize = 16;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        downstreamSubscriber.upstreamSubscription.request(1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
    }

}