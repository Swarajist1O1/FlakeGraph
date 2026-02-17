class DummyClass_113719 {
    @Test
    public void manyToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloRequest> req = Flux.just(HelloRequest.getDefaultInstance());
        Mono<HelloResponse> resp = req.as(stub::sayHelloReqStream);

        StepVerifier.create(resp)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus().getCode() == Status.Code.INTERNAL);
    }

}