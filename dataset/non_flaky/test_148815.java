class DummyClass_148815 {
    @Test
    public void GetTeamsChannelIdBadChannelData() {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");
        String channelId = activity.teamsGetChannelId();
        Assert.assertNull(channelId);
    }

}