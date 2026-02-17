class DummyClass_148806 {
    @Test
    public void GetReplyConversationReference() {
        Activity activity = createActivity();

        ResourceResponse reply = new ResourceResponse();
        reply.setId("1234");

        ConversationReference conversationReference = activity.getReplyConversationReference(reply);

        Assert.assertEquals(reply.getId(), conversationReference.getActivityId());
        Assert.assertEquals(activity.getFrom().getId(), conversationReference.getUser().getId());
        Assert.assertEquals(activity.getRecipient().getId(), conversationReference.getBot().getId());
        Assert.assertEquals(activity.getConversation().getId(), conversationReference.getConversation().getId());
        Assert.assertEquals(activity.getLocale(), conversationReference.getLocale());
        Assert.assertEquals(activity.getChannelId(), conversationReference.getChannelId());
        Assert.assertEquals(activity.getServiceUrl(), conversationReference.getServiceUrl());
    }

}