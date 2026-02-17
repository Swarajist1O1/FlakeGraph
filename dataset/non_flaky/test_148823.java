class DummyClass_148823 {
    @Test
    public void TeamsNotifyUserAlertInMeetingBadChannelData() throws JsonProcessingException, IOException {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");

        Assert.assertNull(activity.teamsGetChannelData());
        activity.teamsNotifyUser(true, "externalresourceURL");
        Assert.assertNotNull(activity.teamsGetChannelData().getNotification());
        Assert.assertEquals(activity.teamsGetChannelData().getNotification().getExternalResourceUrl(),
                            "externalresourceURL");
        Assert.assertTrue(activity.teamsGetChannelData().getNotification().getAlertInMeeting());
    }

}