class DummyClass_148886 {
    @Test
    public void TestGetMentions(){
        List<MessageActionsPayloadMention> mentions = new ArrayList<MessageActionsPayloadMention>();
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setMentions(mentions);
        List<MessageActionsPayloadMention> result = messageActionsPayload.getMentions();

        Assert.assertEquals(result, mentions);
    }

}