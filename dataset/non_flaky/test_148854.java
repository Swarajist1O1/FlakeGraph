class DummyClass_148854 {
    @Test
    public void RemoveRecipientMention() {
        Activity activity = createActivity();
        activity.setText("<at>firstName</at> lastName\n");
        String expectedStrippedName = "lastName";

        List<Mention> mentionList = new ArrayList<Mention>();
        Mention mention = new Mention();
        ChannelAccount channelAccount = new ChannelAccount();
        channelAccount.setId(activity.getRecipient().getId());
        channelAccount.setName("firstName");
        mention.setMentioned(channelAccount);
        mentionList.add(mention);
        activity.setMentions(mentionList);

        String strippedActivityText = activity.removeRecipientMention();
        Assert.assertEquals(strippedActivityText, expectedStrippedName);
    }

}