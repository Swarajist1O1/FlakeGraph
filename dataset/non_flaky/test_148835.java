class DummyClass_148835 {
    @Test
    public void CreateTraceActivity() {
        String name = "test-activity";
        String valueType = "string";
        String value = "test-value";
        String label = "test-label";

        Activity activity = Activity.createTraceActivity(name, valueType, value, label);

        Assert.assertEquals(activity.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(activity.getName(), name);
        Assert.assertEquals(activity.getValueType(), valueType);
        Assert.assertEquals(activity.getValue(), value);
        Assert.assertEquals(activity.getLabel(), label);

        Activity secondActivity = Activity.createTraceActivity(name);
        Assert.assertEquals(secondActivity.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(secondActivity.getName(), name);

        Activity thirdActivity = Activity.createTraceActivity(name, null, value, label);
        Assert.assertEquals(thirdActivity.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(thirdActivity.getName(), name);

        Assert.assertTrue(thirdActivity.isType(ActivityTypes.TRACE));
    }

}