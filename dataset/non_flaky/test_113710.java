class DummyClass_113710 {
    @Test
    public void manyToMany() {
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);
        Flux<HelloResponse> resp = Flux.just(HelloRequest.getDefaultInstance()).compose(stub::sayHelloBothStream);
        StepVerifier.create(resp)
                .verifyErrorMatches(t -> t instanceof StatusRuntimeException && ((StatusRuntimeException)t).getStatus() == Status.INTERNAL);
    }

}