class DummyClass_148819 {
    @Test
    public void GetTeamsGetInfoBadChannelData() {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");
        TeamInfo teamInfo = activity.teamsGetTeamInfo();
        Assert.assertNull(teamInfo);
    }

}