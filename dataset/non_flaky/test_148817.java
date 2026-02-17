class DummyClass_148817 {
    @Test
    public void GetTeamsTeamIdNullChannelData() {
        Activity activity = new Activity();
        String channelId = activity.teamsGetTeamId();
        Assert.assertNull(channelId);
    }

}