class DummyClass_20934 {
    @Test
    public void testAddSubscription() throws Exception {
        AddSubscription add = new AddSubscription();
        add.setSubscriptionId("1234");
        add.setMetric("sys.cpu.user");
        testSerialization(add);
    }

}