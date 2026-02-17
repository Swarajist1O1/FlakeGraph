class DummyClass_148828 {
    @Test
    public void CreateContactRelationUpdateActivity() {
        Activity activity = Activity.createContactRelationUpdateActivity();

        Assert.assertEquals(activity.getType(), ActivityTypes.CONTACT_RELATION_UPDATE);
    }

}