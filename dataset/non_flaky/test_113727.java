class DummyClass_113727 {
    @Test
    public void oneToMany() {
        AtomicInteger called = new AtomicInteger(0);
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);

        HelloRequest request = HelloRequest.newBuilder().setName("World").build();
        stub.sayHelloRespStream(request, new LambdaStreamObserver<>(
                response -> {
                    assertThat(response.getMessage()).isIn("Hello World", "Hi World", "Greetings World");
                    called.incrementAndGet();
                }
        ));

        await().atMost(1, TimeUnit.SECONDS).untilAtomic(called, equalTo(3));
    }

}