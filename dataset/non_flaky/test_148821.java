class DummyClass_148821 {
    @Test
    public void TeamsNotifyUserBadChannelData() throws JsonProcessingException, IOException {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");

        TeamsChannelData channelData = activity.teamsGetChannelData();
        Assert.assertNull(channelData);
        activity.teamsNotifyUser();
        Assert.assertNotNull(activity.teamsGetChannelData().getNotification());
    }

}