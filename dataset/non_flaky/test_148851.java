class DummyClass_148851 {
    @Test
    public void EnsureCloneAddsIdIfMissing() {
        Activity testActivity = new Activity(ActivityTypes.COMMAND);
        Assert.assertTrue(testActivity.getId() == null);
        Activity clonedActivity = Activity.clone(testActivity);
        Assert.assertTrue(clonedActivity.getId() != null);
    }

}