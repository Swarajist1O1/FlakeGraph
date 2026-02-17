class DummyClass_38684 {
    @Test
    public void callWithSomeRetries() throws Exception {
        int N = 10;
        MockTime mockTime = new MockTime();
        RandomExponentialRetry backoffRetry = new RandomExponentialRetry();
        assertEquals(N, (int)backoffRetry.retry( () -> testFunction(N), N+1, 100, "SomeRetries", mockTime));
        assertEquals(N, mockTime.sleeps.size());
        for(int i = 0; i < N; i++) {
            assertTrue(mockTime.sleeps.get(i) <=  backoffRetry.waitInMs(i, 100));
        }
        System.out.println("sleeps="+mockTime.sleeps);
    }

}