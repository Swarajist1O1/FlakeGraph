class DummyClass_134020 {
    @Test
    public void testHandleEntryAck() {
        final LogReplicationEntryMsg entry =  LogReplicationEntryMsg
                .newBuilder().build();
        final ResponseMsg response = ResponseMsg.newBuilder().setPayload(
                CorfuMessage.ResponsePayloadMsg.newBuilder()
                        .setLrEntryAck(entry).build()).build();

        ArgumentCaptor<PayloadCase> argument = ArgumentCaptor.forClass(PayloadCase.class);

        lrClient.receive(response);
        verify(handlerMap, atLeast(1)).get(argument.capture());
        Assertions.assertThat(argument.getValue()).isEqualTo(PayloadCase.LR_ENTRY_ACK);
    }

}