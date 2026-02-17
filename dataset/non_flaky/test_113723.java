class DummyClass_113723 {
    @Test
    public void unimplementedMethodShouldFail() {
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        assertThatThrownBy(() -> stub.sayHello(HelloRequest.newBuilder().setName("World").build()))
                .isInstanceOf(StatusRuntimeException.class)
                .hasMessageContaining("UNIMPLEMENTED");
    }

}