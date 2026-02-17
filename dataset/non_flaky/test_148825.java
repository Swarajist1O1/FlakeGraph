class DummyClass_148825 {
    @Test
    public void TeamsGetMeetingInfo() throws JsonProcessingException, IOException {
        Activity activity = new Activity();
        TeamsChannelData channelData = new TeamsChannelData();
        TeamsMeetingInfo meeting = new TeamsMeetingInfo();
        meeting.setId("meetingId");
        channelData.setMeeting(meeting);
        activity.setChannelData(channelData);

        TeamsMeetingInfo meetingInfo = activity.teamsGetMeetingInfo();
        Assert.assertNotNull(meetingInfo);
        Assert.assertEquals(meetingInfo.getId(), "meetingId");
    }

}