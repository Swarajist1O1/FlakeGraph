class DummyClass_113729 {
    @Test
    public void manyToMany() {
        AtomicInteger called = new AtomicInteger(0);
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);

        StreamObserver<HelloRequest> requestStream = stub.sayHelloBothStream(new LambdaStreamObserver<>(
                response -> {
                    assertThat(response.getMessage()).isIn("Hello A and B", "Hello C and D");
                    called.incrementAndGet();
                }
        ));

        requestStream.onNext(HelloRequest.newBuilder().setName("A").build());
        requestStream.onNext(HelloRequest.newBuilder().setName("B").build());
        requestStream.onNext(HelloRequest.newBuilder().setName("C").build());
        requestStream.onNext(HelloRequest.newBuilder().setName("D").build());
        requestStream.onCompleted();

        await().atMost(1, TimeUnit.SECONDS).untilAtomic(called, equalTo(2));
    }

}