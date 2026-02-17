class DummyClass_148818 {
    @Test
    public void GetTeamsGetInfo() throws JsonProcessingException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        Activity activity = objectMapper.readValue(
            ActivityTest.serializedActivityFromTeamsWithoutTeamsChannelIdorTeamId, Activity.class);

        TeamInfo teamsInfo = activity.teamsGetTeamInfo();
        Assert.assertNotNull(teamsInfo);
    }

}