class DummyClass_113720 {
    @Test
    public void manyToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloRequest> req = Flux.just(HelloRequest.getDefaultInstance());
        Flux<HelloResponse> resp = req.compose(stub::sayHelloBothStream);

        StepVerifier.create(resp)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus().getCode() == Status.Code.INTERNAL);
    }

}