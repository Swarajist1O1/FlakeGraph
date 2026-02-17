class DummyClass_148853 {
    @Test
    public void TryGetChannelDataBadChannelData() {
        Activity activity = createActivity();
        activity.setChannelData("badChannelData");
        ResultPair<TeamsChannelData> channelData = activity.tryGetChannelData(
            TeamsChannelData.class
        );
        Assert.assertFalse(channelData.getLeft());
        Assert.assertNull(channelData.getRight());
    }

}