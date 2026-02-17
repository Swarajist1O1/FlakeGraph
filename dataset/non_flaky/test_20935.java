class DummyClass_20935 {
    @Test
    public void testRemoveSubscription() throws Exception {
        RemoveSubscription remove = new RemoveSubscription();
        remove.setSubscriptionId("1234");
        remove.setMetric("sys.cpu.user");
        testSerialization(remove);
    }

}