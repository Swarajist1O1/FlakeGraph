class DummyClass_148845 {
    @Test
    public void GetMentionsNull() {
        Activity activity = createActivity();
        activity.setEntities(null);
        Assert.assertTrue(activity.getMentions() != null);
    }

}