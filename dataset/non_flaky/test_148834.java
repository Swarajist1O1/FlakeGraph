class DummyClass_148834 {
    @Test
    public void CreateInvokeActivity() {
        Activity activity = Activity.createInvokeActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.INVOKE);
    }

}