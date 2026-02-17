class DummyClass_113742 {
    @Test
    public void zeroMessageResponseOneToOne() {
        serverRule.getServiceRegistry().addService(new MissingUnaryResponseService());

        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(serverRule.getChannel());
        Mono<HelloRequest> req = Mono.just(HelloRequest.newBuilder().setName("reactor").build());
        Mono<HelloResponse> resp = req.compose(stub::sayHello);

        StepVerifier.create(resp).verifyErrorMatches(t ->
                t instanceof StatusRuntimeException &&
                ((StatusRuntimeException) t).getStatus().getCode() == Status.Code.CANCELLED);
    }

}