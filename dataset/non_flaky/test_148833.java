class DummyClass_148833 {
    @Test
    public void CreateEventActivity() {
        Activity activity = Activity.createEventActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.EVENT);
    }

}