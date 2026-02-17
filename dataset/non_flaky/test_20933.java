class DummyClass_20933 {
    @Test
    public void testCloseSubscription() throws Exception {
        CloseSubscription close = new CloseSubscription();
        close.setSubscriptionId("1234");
        testSerialization(close);
    }

}