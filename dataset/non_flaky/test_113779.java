class DummyClass_113779 {
    @Test
    public void requestManyChunksRequestsAsSatisfiedAndStopsWhenComplete() {
        int chunkSize = 3;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        downstreamSubscriber.upstreamSubscription.request(9);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(chunkSize);

        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(3);
        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(3);
        send(chunkSubscriber, 1);
        // Chunk satisfied, request next chunk
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(6);

        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(6);
        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(6);
        send(chunkSubscriber, 1);
        // Chunk satisfied, request next chunk
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(9);

        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(9);
        send(chunkSubscriber, 1);
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(9);
        send(chunkSubscriber, 1);
        // Requested satisfied, do not request any more
        assertThat(upstreamSubscription.lastRequested).isEqualTo(chunkSize);
        assertThat(upstreamSubscription.totalRequested).isEqualTo(9);
    }

}