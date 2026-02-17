class DummyClass_148847 {
    @Test
    public void CreateReplyForConversationUpdateActivity() {
        Activity activity = createActivity();
        activity.setType(ActivityTypes.CONVERSATION_UPDATE);
        Activity reply = activity.createReply("test");
        Assert.assertNull(reply.getReplyToId());
    }

}