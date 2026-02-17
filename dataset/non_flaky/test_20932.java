class DummyClass_20932 {
    @Test
    public void testCreateSubscription() throws Exception {
        CreateSubscription create = new CreateSubscription();
        create.setSubscriptionId("1234");
        testSerialization(create);
    }

}