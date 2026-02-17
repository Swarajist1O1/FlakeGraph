class DummyClass_148850 {
    @Test
    public void ActivityCloneTest() throws JsonProcessingException {
        Activity activity = new Activity(ActivityTypes.MESSAGE);
        activity.setAction("TestAction");

        Attachment attachment = new Attachment();
        attachment.setContentType("testContentType");
        attachment.setContentUrl("testContentUrl");
        attachment.setContent("testContent");
        attachment.setName("testName");
        attachment.setThumbnailUrl("testThumbnailUrl");
        attachment.setProperties("testProperty", getTestNode());
        activity.setAttachment(attachment);

        activity.setCallerId("testCallerId");
        activity.setChannelData("testChannelData");
        activity.setCode(EndOfConversationCodes.BOT_TIMED_OUT);

        ConversationAccount conversation = new ConversationAccount("testConversation");
        activity.setConversation(conversation);

        activity.setDeliveryMode("testDeliveryMode");

        List<Entity> entityList = new ArrayList<Entity>();
        Entity entity1 = new Entity();
        entity1.setType("testEntity");
        entityList.add(entity1);
        activity.setEntities(entityList);

        LocalDateTime expiration = LocalDateTime.now();
        activity.setExpiration(expiration);

        ChannelAccount fromChannel = new ChannelAccount("fromChannel");
        activity.setFrom(fromChannel);

        activity.setHistoryDisclosed(true);
        activity.setId("testId");
        activity.setImportance("testImportance");
        activity.setInputHint(InputHints.ACCEPTING_INPUT);
        activity.setLabel("testLabel");

        List<String> listen = new ArrayList<String>();
        listen.add("listen1");
        listen.add("listen2");
        activity.setListenFor(listen);

        activity.setLocalTimeZone("testLocalTimeZone");
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        activity.setLocalTimestamp(offsetDateTime);
        activity.setLocale("testLocale");

        List<ChannelAccount> membersAdded = new ArrayList<ChannelAccount>();
        ChannelAccount firstMember = new ChannelAccount("firstMember");
        ChannelAccount secondMember = new ChannelAccount("secondMember");
        membersAdded.add(firstMember);
        membersAdded.add(secondMember);
        activity.setMembersAdded(membersAdded);

        List<ChannelAccount> membersRemoved = new ArrayList<ChannelAccount>();
        ChannelAccount firstMemberRemoved = new ChannelAccount("firstMember");
        ChannelAccount secondMemberRemoved = new ChannelAccount("secondMember");
        membersRemoved.add(firstMemberRemoved);
        membersRemoved.add(secondMemberRemoved);
        activity.setMembersRemoved(membersRemoved);

        List<Mention> mentions = new ArrayList<Mention>();
        Mention firstMention = new Mention();
        firstMention.setText("testTest");
        firstMention.setMentioned(firstMember);
        Mention secondMention = new Mention();
        secondMention.setText("testTest");
        secondMention.setMentioned(firstMember);
        mentions.add(secondMention);
        activity.setMentions(mentions);

        activity.setName("testName");
        activity.setProperties("testProperty", getTestNode());

        List<MessageReaction> reactionsAdded = new ArrayList<MessageReaction>();
        MessageReaction firstReaction = new MessageReaction();
        firstReaction.setType("testType");
        reactionsAdded.add(firstReaction);
        MessageReaction secondReaction = new MessageReaction();
        secondReaction.setType("testType");
        reactionsAdded.add(secondReaction);
        activity.setReactionsAdded(reactionsAdded);

        List<MessageReaction> reactionsRemoved = new ArrayList<MessageReaction>();
        MessageReaction firstReactionRemoved = new MessageReaction();
        firstReactionRemoved.setType("testType");
        reactionsRemoved.add(firstReactionRemoved);
        MessageReaction secondReactionRemoved = new MessageReaction();
        secondReactionRemoved.setType("testType");
        reactionsRemoved.add(secondReactionRemoved);
        activity.setReactionsRemoved(reactionsRemoved);

        ChannelAccount recipientRemoved = new ChannelAccount();
        recipientRemoved.setId("testRecipient");
        activity.setRecipient(recipientRemoved);

        ConversationReference relatesToReference = new ConversationReference();
        relatesToReference.setActivityId("testActivityId");
        activity.setRelatesTo(relatesToReference);

        activity.setReplyToId("testReplyToId");
        activity.setServiceUrl("testServiceUrl");
        activity.setText("testText");
        activity.setTextFormat(TextFormatTypes.MARKDOWN);

        List<TextHighlight> textHighlights = new ArrayList<TextHighlight>();
        TextHighlight firstTextHighlight = new TextHighlight();
        firstTextHighlight.setText("testText");
        textHighlights.add(firstTextHighlight);
        TextHighlight secondTextHighlight = new TextHighlight();
        secondTextHighlight.setText("testText");
        textHighlights.add(secondTextHighlight);
        activity.setTextHighlights(textHighlights);

        OffsetDateTime timestamp = OffsetDateTime.now();
        activity.setTimestamp(timestamp);

        activity.setTopicName("testTopicName");
        activity.setType("testType");
        activity.setValue("testValue");
        activity.setValueType("testValueType");

        Activity clonedActivity = Activity.clone(activity);

        Assert.assertEquals(activity.getAction(), clonedActivity.getAction());
        Assert.assertEquals(activity.getCallerId(), clonedActivity.getCallerId());
        Assert.assertEquals(activity.getChannelData(), clonedActivity.getChannelData());
        Assert.assertEquals(activity.getDeliveryMode(), clonedActivity.getDeliveryMode());
        Assert.assertEquals(activity.getId(), clonedActivity.getId());
        Assert.assertEquals(activity.getImportance(), clonedActivity.getImportance());
        Assert.assertEquals(activity.getLabel(), clonedActivity.getLabel());
        Assert.assertEquals(activity.getLocalTimezone(), clonedActivity.getLocalTimezone());
        Assert.assertEquals(activity.getLocale(), clonedActivity.getLocale());
        Assert.assertEquals(activity.getName(), clonedActivity.getName());
        Assert.assertEquals(activity.getReplyToId(), clonedActivity.getReplyToId());
        Assert.assertEquals(activity.getServiceUrl(), clonedActivity.getServiceUrl());
        Assert.assertEquals(activity.getSpeak(), clonedActivity.getSpeak());
        Assert.assertEquals(activity.getSummary(), clonedActivity.getSummary());
        Assert.assertEquals(activity.getText(), clonedActivity.getText());
        Assert.assertEquals(activity.getTopicName(), clonedActivity.getTopicName());
        Assert.assertEquals(activity.getType(), clonedActivity.getType());
        Assert.assertEquals(activity.getValue(), clonedActivity.getValue());
        Assert.assertEquals(activity.getValueType(), clonedActivity.getValueType());
        Assert.assertEquals(activity.getAttachmentLayout(), clonedActivity.getAttachmentLayout());
        Assert.assertEquals(activity.getAttachments().get(0).getName(),
                            clonedActivity.getAttachments().get(0).getName());
        Assert.assertEquals(activity.getChannelData(ChannelAccount.class).getId(),
                            clonedActivity.getChannelData(ChannelAccount.class).getId());
        Assert.assertEquals(activity.getCode(), clonedActivity.getCode());
        Assert.assertEquals(activity.getConversation().getName(), clonedActivity.getConversation().getName());
        Assert.assertEquals(activity.getConversationReference().getChannelId(),
                            clonedActivity.getConversationReference().getChannelId());
        Assert.assertEquals(activity.getEntities().get(0).getType(), clonedActivity.getEntities().get(0).getType());
        Assert.assertEquals(activity.getExpiration(), clonedActivity.getExpiration());
        Assert.assertEquals(activity.getFrom().getId(), clonedActivity.getFrom().getId());
        Assert.assertEquals(activity.getInputHint(), clonedActivity.getInputHint());
        Assert.assertEquals(activity.getListenFor(), clonedActivity.getListenFor());
        Assert.assertEquals(activity.getLocalTimestamp(), clonedActivity.getLocalTimestamp());
        Assert.assertEquals(activity.getMembersAdded().get(0).getId(), clonedActivity.getMembersAdded().get(0).getId());
        Assert.assertEquals(activity.getMembersRemoved().get(0).getId(),
                            clonedActivity.getMembersRemoved().get(0).getId());
        Assert.assertEquals(activity.getMentions().get(0).getText(), clonedActivity.getMentions().get(0).getText());
        Assert.assertEquals(activity.getProperties(), clonedActivity.getProperties());
        Assert.assertEquals(activity.getReactionsAdded().get(0).getType(),
                            clonedActivity.getReactionsAdded().get(0).getType());
        Assert.assertEquals(activity.getReactionsRemoved().get(0).getType(),
                            clonedActivity.getReactionsRemoved().get(0).getType());
        Assert.assertEquals(activity.getRecipient().getId(), clonedActivity.getRecipient().getId());
        Assert.assertEquals(activity.getRelatesTo().getActivityId(), clonedActivity.getRelatesTo().getActivityId());
        // add activity.getReplyConversationReference(reply)
        Assert.assertEquals(activity.getSuggestedActions(), clonedActivity.getSuggestedActions());
        Assert.assertEquals(activity.getTextFormat(), clonedActivity.getTextFormat());
        Assert.assertEquals(activity.getTextHighlights(), clonedActivity.getTextHighlights());
        Assert.assertEquals(activity.getTimestamp(), clonedActivity.getTimestamp());
    }

}