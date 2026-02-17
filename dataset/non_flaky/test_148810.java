class DummyClass_148810 {
    @Test
    public void ApplyConversationReferenceOverloadAlternatePaths() {
        Activity activity = createActivity();

        ConversationReference conversationReference = new ConversationReference();
        conversationReference.setChannelId("123");
        conversationReference.setServiceUrl("serviceUrl");
        ConversationAccount conversation = new ConversationAccount();
        conversation.setId("456");
        conversationReference.setConversation(conversation);
        ChannelAccount userAccount = new ChannelAccount();
        userAccount.setId("abc");
        conversationReference.setUser(userAccount);
        ChannelAccount botAccount = new ChannelAccount();
        botAccount.setId("def");
        conversationReference.setBot(botAccount);
        conversationReference.setActivityId(null);
        conversationReference.setLocale(null);

        activity.applyConversationReference(conversationReference, false);

        Assert.assertEquals(conversationReference.getChannelId(), activity.getChannelId());
        Assert.assertEquals("en-uS", activity.getLocale());
        Assert.assertEquals(conversationReference.getServiceUrl(), activity.getServiceUrl());
        Assert.assertEquals(conversationReference.getConversation().getId(), activity.getConversation().getId());

        Assert.assertEquals(conversationReference.getBot().getId(), activity.getFrom().getId());
        Assert.assertEquals(conversationReference.getUser().getId(), activity.getRecipient().getId());
        Assert.assertEquals(conversationReference.getActivityId(), activity.getReplyToId());

        activity.applyConversationReference(conversationReference, true);

        Assert.assertEquals(conversationReference.getChannelId(), activity.getChannelId());
        Assert.assertEquals("en-uS", activity.getLocale());
        Assert.assertEquals(conversationReference.getServiceUrl(), activity.getServiceUrl());
        Assert.assertEquals(conversationReference.getConversation().getId(), activity.getConversation().getId());

        Assert.assertEquals(conversationReference.getUser().getId(), activity.getFrom().getId());
        Assert.assertEquals(conversationReference.getBot().getId(), activity.getRecipient().getId());
        Assert.assertEquals(conversationReference.getActivityId(), activity.getReplyToId());
    }

}