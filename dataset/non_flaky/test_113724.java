class DummyClass_113724 {
    @Test
    public void getChannelWorks() {
        ManagedChannel channel = serverRule.getChannel();
        ReactorGreeterGrpc.ReactorGreeterStub stub = ReactorGreeterGrpc.newReactorStub(channel);

        assertThat(stub.getChannel()).isEqualTo(channel);
    }

}