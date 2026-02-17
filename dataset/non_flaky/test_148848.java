class DummyClass_148848 {
    @Test
    public void CreateTrace() {
        Activity activity = createActivity();

        String name = "test-activity";
        String value = "test-value";
        String valueType = "string";
        String label = "test-label";

        Activity trace = activity.createTrace(name, value, valueType, label);

        Assert.assertEquals(trace.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(trace.getName(), name);
        Assert.assertEquals(trace.getValue(), value);
        Assert.assertEquals(trace.getValueType(), valueType);
        Assert.assertEquals(trace.getLabel(), label);

        Activity secondActivity = createActivity();
        secondActivity.setRecipient(null);
        secondActivity.setFrom(null);
        Activity secondTrace = secondActivity.createTrace(name, value, null, label);
        Assert.assertEquals(secondTrace.getType(), ActivityTypes.TRACE);
        Assert.assertEquals(secondTrace.getName(), name);
        Assert.assertEquals(secondTrace.getValue(), value);
        Assert.assertEquals(secondTrace.getValueType(), value.getClass().getTypeName());
        Assert.assertEquals(secondTrace.getLabel(), label);
        Assert.assertTrue(secondTrace.getRecipient() != null);
        Assert.assertTrue(secondTrace.getFrom() != null);
    }

}