class DummyClass_113796 {
    @Test
    public void prematureResponseStreamDisposalShouldNotThrowUnhandledException() throws Exception {
        TestService svc = new TestService();
        serverRule.getServiceRegistry().addService(svc);

        RxNumbersGrpc.RxNumbersStub stub = RxNumbersGrpc.newRxStub(serverRule.getChannel());

        // slowly process the response stream
        Disposable subscription = stub.responsePressure(Empty.getDefaultInstance()).subscribe(n -> {
            Thread.sleep(1000);
        });

        subscription.dispose();

        Thread.sleep(200);
        errorRule.verifyNoError();
    }

}