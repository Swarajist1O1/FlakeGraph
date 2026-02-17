class DummyClass_38682 {
    @Test
    public void callWithNoRetries() throws Exception {
        MockTime mockTime = new MockTime();
        RandomExponentialRetry backoffRetry = new RandomExponentialRetry();
        assertEquals(0, (int)backoffRetry.retry( () -> testFunction(0), 3, 100, "NoRetries", mockTime));
        assertEquals(0L, mockTime.totalMs.get());
        assertEquals(0L, mockTime.sleeps.size());
    }

}