class DummyClass_134018 {
    @Test
    public void testHandleLeadershipLoss() {
        final LogReplicationLeadershipLossResponseMsg leadershipLoss =  LogReplicationLeadershipLossResponseMsg
                .newBuilder().build();
        final ResponseMsg response = ResponseMsg.newBuilder().setPayload(
                CorfuMessage.ResponsePayloadMsg.newBuilder()
                        .setLrLeadershipLoss(leadershipLoss).build()).build();
        lrClient.receive(response);

        ArgumentCaptor<LogReplicationRuntimeEvent> argument = ArgumentCaptor.forClass(LogReplicationRuntimeEvent.class);
        verify(lrFsm).input(argument.capture());
        Assertions.assertThat(argument.getValue().getType()).isEqualTo(LogReplicationRuntimeEventType.REMOTE_LEADER_LOSS);
    }

}