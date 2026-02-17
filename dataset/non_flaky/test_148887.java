class DummyClass_148887 {
    @Test
    public void TestGetReactions(){
        List<MessageActionsPayloadReaction> reactions = new ArrayList<MessageActionsPayloadReaction>();
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setReactions(reactions);
        List<MessageActionsPayloadReaction> result = messageActionsPayload.getReactions();

        Assert.assertEquals(result, reactions);
    }

}