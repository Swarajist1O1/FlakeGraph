class DummyClass_148857 {
    @Test
    public void RemoveRecipientMentionImmutableNoRecipient() {
        Activity activity = createActivity();
        activity.setText("<at>firstName</at> lastName\n");
        String expectedStrippedName = "<at>firstName</at> lastName\n";

        List<Mention> mentionList = new ArrayList<Mention>();
        Mention mention = new Mention();
        ChannelAccount channelAccount = new ChannelAccount();
        channelAccount.setId(activity.getRecipient().getId());
        channelAccount.setName("firstName");
        mention.setMentioned(channelAccount);
        mentionList.add(mention);
        activity.setMentions(mentionList);
        activity.setRecipient(null);

        String strippedActivityText = Activity.removeRecipientMentionImmutable(activity);
        Assert.assertEquals(strippedActivityText, expectedStrippedName);
    }

}