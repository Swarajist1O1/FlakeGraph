class DummyClass_135787 {
    @Test
    public void testShutdownWithWait() {
        // other unit tests will probably have run before this so get the count
        int count = Factory.threadPools.size();

        HystrixThreadPool pool = Factory.getInstance(HystrixThreadPoolKey.Factory.asKey("threadPoolFactoryTest"),
                HystrixThreadPoolProperties.Setter.getUnitTestPropertiesBuilder());

        assertEquals(count + 1, Factory.threadPools.size());
        assertFalse(pool.getExecutor().isShutdown());

        Factory.shutdown(1, TimeUnit.SECONDS);

        // ensure all pools were removed from the cache
        assertEquals(0, Factory.threadPools.size());
        assertTrue(pool.getExecutor().isShutdown());
    }

}