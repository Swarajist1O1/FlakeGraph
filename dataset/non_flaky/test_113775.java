class DummyClass_113775 {
    @Test
    public void applySubscribes() {
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(16);

        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        assertThat(chunkSubscriber).isNotNull();

        chunkSubscriber.onSubscribe(upstreamSubscription);
        assertThat(downstreamSubscriber.upstreamSubscription).isNotNull();
    }

}