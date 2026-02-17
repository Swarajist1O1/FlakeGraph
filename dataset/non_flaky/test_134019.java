class DummyClass_134019 {
    @Test
    public void testHandleLeadershipResponse() {
        final LogReplicationLeadershipResponseMsg leadershipResponse = LogReplicationLeadershipResponseMsg
                .newBuilder().build();
        final ResponseMsg response = ResponseMsg.newBuilder().setPayload(
                CorfuMessage.ResponsePayloadMsg.newBuilder()
                        .setLrLeadershipResponse(leadershipResponse).build()).build();

        ArgumentCaptor<PayloadCase> argument = ArgumentCaptor.forClass(PayloadCase.class);

        lrClient.receive(response);
        verify(handlerMap, atLeast(1)).get(argument.capture());
        Assertions.assertThat(argument.getValue()).isEqualTo(PayloadCase.LR_LEADERSHIP_RESPONSE);
    }

}