class DummyClass_148852 {
    @Test
    public void TryGetChannelData() {
        Activity activity = createActivity();
        ResultPair<TeamsChannelData> channelData = activity.tryGetChannelData(
            TeamsChannelData.class
        );

        activity.setChannelData(new TeamsChannelData());
        channelData = activity.tryGetChannelData(
            TeamsChannelData.class
        );
        Assert.assertTrue(channelData.getLeft());

        activity.setChannelData(null);
        Assert.assertNull(activity.teamsGetChannelData());
    }

}