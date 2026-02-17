class DummyClass_148826 {
    @Test
    public void TeamsGetMeetingInfoBadChannelData() throws JsonProcessingException, IOException {
        Activity activity = new Activity();
        activity.setChannelData("badChannelData");

        TeamsMeetingInfo meetingInfo = activity.teamsGetMeetingInfo();
        Assert.assertNull(meetingInfo);
    }

}