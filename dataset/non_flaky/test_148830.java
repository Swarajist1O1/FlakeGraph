class DummyClass_148830 {
    @Test
    public void CreateTypingActivity() {
        Activity activity = Activity.createTypingActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.TYPING);
    }

}