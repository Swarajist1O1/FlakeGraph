class DummyClass_113797 {
    @Test
    public void oneToOne() {
        RxGreeterGrpc.RxGreeterStub stub = RxGreeterGrpc.newRxStub(channel);
        Single<HelloResponse> resp = Single.just(HelloRequest.getDefaultInstance()).compose(stub::sayHello);
        TestObserver<HelloResponse> test = resp.test();

        test.awaitTerminalEvent(3, TimeUnit.SECONDS);
        test.assertError(t -> t instanceof StatusRuntimeException);
        test.assertError(t -> ((StatusRuntimeException)t).getStatus() == Status.INTERNAL);
    }

}