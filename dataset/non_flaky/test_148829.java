class DummyClass_148829 {
    @Test
    public void CreateConversationUpdateActivity() {
        Activity activity = Activity.createConversationUpdateActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.CONVERSATION_UPDATE);
    }

}