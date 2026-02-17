class DummyClass_134021 {
    @Test
    public void testHandleMetadataResponse() {
        final LogReplicationMetadataResponseMsg entry =  LogReplicationMetadataResponseMsg
                .newBuilder().build();
        final ResponseMsg response = ResponseMsg.newBuilder().setPayload(
                CorfuMessage.ResponsePayloadMsg.newBuilder()
                        .setLrMetadataResponse(entry).build()).build();

        ArgumentCaptor<PayloadCase> argument = ArgumentCaptor.forClass(PayloadCase.class);

        lrClient.receive(response);
        verify(handlerMap, atLeast(1)).get(argument.capture());
        Assertions.assertThat(argument.getValue()).isEqualTo(PayloadCase.LR_METADATA_RESPONSE);
    }

}