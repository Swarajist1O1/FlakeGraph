class DummyClass_113781 {
    @Test
    public void errorPropagatesDown() {
        int chunkSize = 3;
        ReactiveBackpressureChunker<Object> chunker = new ReactiveBackpressureChunker<Object>(chunkSize);
        UpstreamSubscription upstreamSubscription = new UpstreamSubscription();
        DownstreamSubscriber downstreamSubscriber = new DownstreamSubscriber();

        Subscriber<Object> chunkSubscriber = chunker.apply(downstreamSubscriber);
        chunkSubscriber.onSubscribe(upstreamSubscription);

        Throwable t = new Throwable();
        chunkSubscriber.onError(t);
        assertThat(downstreamSubscriber.lastThrowable).isEqualTo(t);
    }

}