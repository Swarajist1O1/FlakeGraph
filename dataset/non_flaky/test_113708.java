class DummyClass_113708 {
    @Test
    public void oneToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloResponse> resp = Mono.just(HelloRequest.getDefaultInstance()).as(stub::sayHelloRespStream);
        Flux<HelloResponse> test = resp
                .doOnNext(System.out::println)
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("Completed"))
                .doOnCancel(() -> System.out.println("Client canceled"));

        StepVerifier.create(test)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus() == Status.INTERNAL);
    }

}