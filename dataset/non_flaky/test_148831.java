class DummyClass_148831 {
    @Test
    public void CreateHandoffActivity() {
        Activity activity = Activity.createHandoffActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.HANDOFF);
    }

}