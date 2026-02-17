class DummyClass_148860 {
    @Test
    public void RemoveRecipientMentionTextNoText() {
        Activity activity = createActivity();
        activity.setText("");
        String expectedStrippedName = "";

        List<Mention> mentionList = new ArrayList<Mention>();
        Mention mention = new Mention();
        mention.setText("lastName");
        ChannelAccount channelAccount = new ChannelAccount();
        channelAccount.setId(activity.getRecipient().getId());
        channelAccount.setName("firstName");
        mention.setMentioned(channelAccount);
        mentionList.add(mention);
        activity.setMentions(mentionList);

        String strippedActivityText = Activity.removeMentionTextImmutable(activity, "lastName");
        Assert.assertEquals(strippedActivityText, expectedStrippedName);
    }

}