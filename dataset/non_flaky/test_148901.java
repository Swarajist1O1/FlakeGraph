class DummyClass_148901 {
    @Test
    public void testClone() {
        Activity activity = createActivity();
        Activity resultActivity = (Activity) Serialization.clone((Object) activity);
        Assert.assertEquals(activity.getId(), resultActivity.getId());
        Assert.assertEquals(activity.getFrom().getId(), resultActivity.getFrom().getId());
        Assert.assertEquals(activity.getConversation().getId(), resultActivity.getConversation().getId());
    }

}