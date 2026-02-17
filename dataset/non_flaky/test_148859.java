class DummyClass_148859 {
    @Test
    public void RemoveRecipientMentionTextNoId() {
        Activity activity = createActivity();
        activity.setText("<at>firstName</at> lastName\n");
        String expectedStrippedName = "<at>firstName</at> lastName\n";

        List<Mention> mentionList = new ArrayList<Mention>();
        Mention mention = new Mention();
        mention.setText("lastName");
        ChannelAccount channelAccount = new ChannelAccount();
        channelAccount.setId(activity.getRecipient().getId());
        channelAccount.setName("firstName");
        mention.setMentioned(channelAccount);
        mentionList.add(mention);
        activity.setMentions(mentionList);

        String strippedActivityText = Activity.removeMentionTextImmutable(activity, null);
        Assert.assertEquals(strippedActivityText, expectedStrippedName);
    }

}