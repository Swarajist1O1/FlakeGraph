class DummyClass_148814 {
    @Test
    public void GetInformationForMicrosoftTeams() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        Activity activity = objectMapper.readValue(ActivityTest.serializedActivityFromTeams, Activity.class);
        Assert.assertEquals("19:123cb42aa5a0a7e56f83@thread.skype", activity.teamsGetChannelId());
        Assert.assertEquals("19:104f2cb42aa5a0a7e56f83@thread.skype", activity.teamsGetTeamId());
        Assert.assertEquals(true, activity.isTeamsActivity());

        activity = objectMapper.readValue(ActivityTest.serializedActivityFromTeamsWithoutTeamsChannelIdorTeamId,
                Activity.class);

        Assert.assertEquals("channel_id", activity.teamsGetChannelId());
        Assert.assertEquals("team_id", activity.teamsGetTeamId());

        TeamsChannelData teamsChannelData = activity.getChannelData(TeamsChannelData.class);
        Assert.assertEquals("channel_id", teamsChannelData.getChannel().getId());
        Assert.assertEquals("channel_name", teamsChannelData.getChannel().getName());
        Assert.assertEquals("team_id", teamsChannelData.getTeam().getId());
        Assert.assertEquals("team_name", teamsChannelData.getTeam().getName());
        Assert.assertEquals("aad_groupid", teamsChannelData.getTeam().getAadGroupId());
        Assert.assertEquals(true, teamsChannelData.getNotification().getAlert());
        Assert.assertEquals("teamMemberAdded", teamsChannelData.getEventType());
        Assert.assertEquals("tenant_id", teamsChannelData.getTenant().getId());
    }

}