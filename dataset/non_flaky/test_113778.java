class DummyClass_113778 {
    @Test
    public void requestManyGetsAChunkFirst() {
        int chunkSize = 16;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        downstreamSubscriber.upstreamSubscription.request(256);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
    }

}