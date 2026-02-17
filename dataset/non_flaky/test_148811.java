class DummyClass_148811 {
    @Test
    public void CreateTraceAllowsNullRecipient() {
        Activity activity = createActivity();
        activity.setRecipient(null);
        Activity trace = activity.createTrace("test");

        Assert.assertNull(trace.getFrom().getId());
    }

}