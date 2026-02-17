class DummyClass_38681 {
    @Test
    public void testExponentialWait() {
        RandomExponentialRetry backoffRetry = new RandomExponentialRetry(5);
        assertEquals(backoffRetry.waitInMs(0, 100), 100L);
        assertEquals(backoffRetry.waitInMs(1, 100), 200L);
        assertEquals(backoffRetry.waitInMs(2, 100), 400L);
        assertEquals(backoffRetry.waitInMs(3, 100), 800L);
        assertEquals(backoffRetry.waitInMs(4, 100), 1600L);
        assertEquals(backoffRetry.waitInMs(5, 100), 3200L);
        assertEquals(backoffRetry.waitInMs(6, 100), 5000L);
    }

}