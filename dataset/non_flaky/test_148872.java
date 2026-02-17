class DummyClass_148872 {
    @Test
    public void TestGetReplyToId(){
        String replyToId = "testReplyToId";
        MessageActionsPayload messageActionsPayload = new MessageActionsPayload();
        messageActionsPayload.setReplyToId(replyToId);
        String result = messageActionsPayload.getReplyToId();

        Assert.assertEquals(result, replyToId);
    }

}