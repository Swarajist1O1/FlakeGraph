class DummyClass_148858 {
    @Test
    public void RemoveRecipientMentionText() {
        Activity activity = createActivity();
        activity.setText("<at>firstName</at> lastName\n");
        String expectedStrippedName = "<at>firstName</at>";

        List<Mention> mentionList = new ArrayList<Mention>();
        Mention mention = new Mention();
        mention.setText("lastName");
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