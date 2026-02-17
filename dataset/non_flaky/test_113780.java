class DummyClass_113780 {
    @Test
    public void completePropagatesDown() {
        int chunkSize = 3;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        chunkSubscriber.onComplete();
        assertThat(downstreamSubscriber.isComplete).isTrue();
    }

}