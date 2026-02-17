class DummyClass_148805 {
    @Test
    public void GetConversationReference() {
        Activity activity = createActivity();
        ConversationReference conversationReference = activity.getConversationReference();

        Assert.assertEquals(activity.getId(), conversationReference.getActivityId());
        Assert.assertEquals(activity.getFrom().getId(), conversationReference.getUser().getId());
        Assert.assertEquals(activity.getRecipient().getId(), conversationReference.getBot().getId());
        Assert.assertEquals(activity.getConversation().getId(), conversationReference.getConversation().getId());
        Assert.assertEquals(activity.getLocale(), conversationReference.getLocale());
        Assert.assertEquals(activity.getChannelId(), conversationReference.getChannelId());
        Assert.assertEquals(activity.getServiceUrl(), conversationReference.getServiceUrl());

        activity.setType(ActivityTypes.CONVERSATION_UPDATE);
        conversationReference = activity.getConversationReference();
        Assert.assertNull(conversationReference.getActivityId());

    }

}