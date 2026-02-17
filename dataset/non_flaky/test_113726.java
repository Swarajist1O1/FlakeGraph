class DummyClass_113726 {
    @Test
    public void oneToOne() {
        AtomicBoolean called = new AtomicBoolean(false);
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("World").build();
        stub.sayHello(request, new LambdaStreamObserver<>(
                response -> {
                    assertThat(response.getMessage()).isEqualTo("Hello World");
                    called.set(true);
                }
        ));

        await().atMost(1, TimeUnit.SECONDS).untilTrue(called);
    }

}