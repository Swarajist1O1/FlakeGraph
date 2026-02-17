class DummyClass_148846 {
    @Test
    public void CreateTraceForConversationUpdateActivity() {
        Activity activity = createActivity();
        activity.setType(ActivityTypes.CONVERSATION_UPDATE);
        Activity trace = activity.createTrace("test");
        Assert.assertNull(trace.getReplyToId());
    }

}