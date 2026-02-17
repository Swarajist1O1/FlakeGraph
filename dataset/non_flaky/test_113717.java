class DummyClass_113717 {
    @Test
    public void oneToOne() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Mono<HelloResponse> resp = Mono.just(HelloRequest.getDefaultInstance()).compose(stub::sayHello);

        StepVerifier.create(resp)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus().getCode() == Status.Code.INTERNAL);
    }

}