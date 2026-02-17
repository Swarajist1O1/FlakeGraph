class DummyClass_148809 {
    @Test
    public void ApplyConversationReferenceOverload() {
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
        conversationReference.setActivityId("12345");
        // Intentionally oddly-cased to check that it isn't defaulted somewhere, but
        // tests stay in English
        conversationReference.setLocale("en-uS");

        activity.applyConversationReference(conversationReference);

        Assert.assertEquals(conversationReference.getChannelId(), activity.getChannelId());
        Assert.assertEquals(conversationReference.getLocale(), activity.getLocale());
        Assert.assertEquals(conversationReference.getServiceUrl(), activity.getServiceUrl());
        Assert.assertEquals(conversationReference.getConversation().getId(), activity.getConversation().getId());

        Assert.assertEquals(conversationReference.getBot().getId(), activity.getFrom().getId());
        Assert.assertEquals(conversationReference.getUser().getId(), activity.getRecipient().getId());
        Assert.assertEquals(conversationReference.getActivityId(), activity.getReplyToId());
    }

}