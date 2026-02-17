class DummyClass_113725 {
    @Test
    public void settingCallOptionsWorks() {
        ManagedChannel channel = serverRule.getChannel();
        Deadline deadline = Deadline.after(42, TimeUnit.SECONDS);

        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel).withDeadline(deadline);

        assertThat(stub.getCallOptions().getDeadline()).isEqualTo(deadline);
    }

}