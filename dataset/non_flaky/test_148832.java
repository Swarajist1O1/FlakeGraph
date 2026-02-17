class DummyClass_148832 {
    @Test
    public void CreateEndOfConversationActivity() {
        Activity activity = Activity.createEndOfConversationActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.END_OF_CONVERSATION);
    }

}