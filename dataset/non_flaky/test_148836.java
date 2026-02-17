class DummyClass_148836 {
    @Test
    public void CreateTraceActivityWithoutValueType() {
        String name = "test-activity";
        String value = "test-value";
        String label = "test-label";

        Activity activity = Activity.createTraceActivity(name, null, value, label);

        Assert.assertEquals(activity.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(activity.getValueType(), value.getClass().getTypeName());
        Assert.assertEquals(activity.getLabel(), label);
    }

}