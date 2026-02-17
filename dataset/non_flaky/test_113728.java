class DummyClass_113728 {
    @Test
    public void manyToOne() {
        AtomicBoolean called = new AtomicBoolean(false);
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);

        StreamObserver<HelloRequest> requestStream = stub.sayHelloReqStream(new LambdaStreamObserver<>(
                response -> {
                    assertThat(response.getMessage()).isEqualTo("Hello A and B and C");
                    called.set(true);
                }
        ));

        requestStream.onNext(HelloRequest.newBuilder().setName("A").build());
        requestStream.onNext(HelloRequest.newBuilder().setName("B").build());
        requestStream.onNext(HelloRequest.newBuilder().setName("C").build());
        requestStream.onCompleted();

        await().atMost(1, TimeUnit.SECONDS).untilTrue(called);
    }

}