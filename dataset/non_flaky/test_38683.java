class DummyClass_38683 {
    @Test(expectedExceptions = { IOException.class })
    public void callWithExhaustedRetries() throws Exception {
        MockTime mockTime = new MockTime();
        RandomExponentialRetry backoffRetry = new RandomExponentialRetry();
        assertEquals(4, (int)backoffRetry.retry( () -> testFunction(4), 3, 100, "ExhautstedRetries", mockTime));
    }

}