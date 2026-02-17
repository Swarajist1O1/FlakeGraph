class DummyClass_148824 {
    @Test
    public void TeamsGetMeetingInfoNull() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Activity activity = objectMapper.readValue(
            ActivityTest.serializedActivityFromTeamsWithoutNotificationTeamsChannelIdOrTeamId, Activity.class);

        TeamsMeetingInfo meetingInfo = activity.teamsGetMeetingInfo();
        Assert.assertNull(meetingInfo);
    }

}