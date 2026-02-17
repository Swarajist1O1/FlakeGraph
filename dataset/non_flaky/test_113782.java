class DummyClass_113782 {
    @Test
    public void cancelPropagatesUp() {
        int chunkSize = 3;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        downstreamSubscriber.upstreamSubscription.cancel();
        assertThat(upstreamSubscription.isCancelled).isTrue();
    }

}