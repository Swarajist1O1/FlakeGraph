class DummyClass_113743 {
    @Test
    public void zeroMessageResponseManyToOne() {
        serverRule.getServiceRegistry().addService(new MissingUnaryResponseService());

        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(serverRule.getChannel());
        Flux<HelloRequest> req = Flux.just(
                HelloRequest.newBuilder().setName("a").build(),
                HelloRequest.newBuilder().setName("b").build(),
                HelloRequest.newBuilder().setName("c").build());

        Mono<HelloResponse> resp = req.as(stub::sayHelloReqStream);

        StepVerifier.create(resp).verifyErrorMatches(t ->
                t instanceof StatusRuntimeException &&
                ((StatusRuntimeException) t).getStatus().getCode() == Status.Code.CANCELLED);
    }

}