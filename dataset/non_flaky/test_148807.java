class DummyClass_148807 {
    @Test
    public void ApplyConversationReference_isIncoming() {
        Activity activity = createActivity();

        ConversationReference conversationReference = new ConversationReference();
        conversationReference.setChannelId("cr_123");
        conversationReference.setServiceUrl("cr_serviceUrl");
        ConversationAccount conversation = new ConversationAccount();
        conversation.setId("cr_456");
        conversationReference.setConversation(conversation);
        ChannelAccount userAccount = new ChannelAccount();
        userAccount.setId("cr_abc");
        conversationReference.setUser(userAccount);
        ChannelAccount botAccount = new ChannelAccount();
        botAccount.setId("cr_def");
        conversationReference.setBot(botAccount);
        conversationReference.setActivityId("cr_12345");
        // Intentionally oddly-cased to check that it isn't defaulted somewhere, but
        // tests stay in English
        conversationReference.setLocale("en-uS");

        activity.applyConversationReference(conversationReference, true);

        Assert.assertEquals(conversationReference.getChannelId(), activity.getChannelId());
        Assert.assertEquals(conversationReference.getLocale(), activity.getLocale());
        Assert.assertEquals(conversationReference.getServiceUrl(), activity.getServiceUrl());
        Assert.assertEquals(conversationReference.getConversation().getId(), activity.getConversation().getId());

        Assert.assertEquals(conversationReference.getUser().getId(), activity.getFrom().getId());
        Assert.assertEquals(conversationReference.getBot().getId(), activity.getRecipient().getId());
        Assert.assertEquals(conversationReference.getActivityId(), activity.getId());
    }

}