class DummyClass_148820 {
    @Test
    public void TeamsNotifyUser() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Activity activity = objectMapper.readValue(
            ActivityTest.serializedActivityFromTeamsWithoutNotificationTeamsChannelIdOrTeamId, Activity.class);

        TeamsChannelData channelData = activity.teamsGetChannelData();
        Assert.assertNull(channelData.getNotification());
        activity.teamsNotifyUser();
        Assert.assertNotNull(activity.teamsGetChannelData().getNotification());
    }

}