class DummyClass_113709 {
    @Test
    public void manyToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<HelloResponse> resp = Flux.just(HelloRequest.getDefaultInstance()).as(stub::sayHelloReqStream);
        StepVerifier.create(resp)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus() == Status.INTERNAL);
    }

}