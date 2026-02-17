class DummyClass_148899 {
    @Test
    public void testGetAs() {
        Activity activity = createActivity();
        JsonNode activityNode = Serialization.objectToTree(activity);
        Activity resultActivity = Serialization.getAs(activityNode, Activity.class);
        Assert.assertEquals(activity.getId(), resultActivity.getId());
        Assert.assertEquals(activity.getFrom().getId(), resultActivity.getFrom().getId());
        Assert.assertEquals(activity.getConversation().getId(), resultActivity.getConversation().getId());
    }

}