class DummyClass_148816 {
    @Test
    public void GetTeamsTeamIdBadChannelData() {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");
        String channelId = activity.teamsGetTeamId();
        Assert.assertNull(channelId);
    }

}