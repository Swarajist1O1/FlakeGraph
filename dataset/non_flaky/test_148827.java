class DummyClass_148827 {
    @Test
    public void CreateMessageActivity() {
        Activity activity = Activity.createMessageActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.MESSAGE);
    }

}