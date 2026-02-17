class DummyClass_148822 {
    @Test
    public void TeamsNotifyUserAlertInMeeting() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Activity activity = objectMapper.readValue(
            ActivityTest.serializedActivityFromTeamsWithoutNotificationTeamsChannelIdOrTeamId, Activity.class);

        TeamsChannelData channelData = activity.teamsGetChannelData();
        Assert.assertNull(channelData.getNotification());
        activity.teamsNotifyUser(true, "externalresourceURL");
        Assert.assertNotNull(activity.teamsGetChannelData().getNotification());
        Assert.assertEquals(activity.teamsGetChannelData().getNotification().getExternalResourceUrl(),
                            "externalresourceURL");
        Assert.assertTrue(activity.teamsGetChannelData().getNotification().getAlertInMeeting());
    }

}