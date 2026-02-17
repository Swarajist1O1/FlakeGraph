class DummyClass_84577 {
    @Test
    public void testNodes20() throws Exception {
        int testIterations = 20;
        final CountDownLatch latch = new CountDownLatch(testIterations);
        final AtomicInteger failureCounter = new AtomicInteger();

        for (int i = 0; i < testIterations; i++) {
            runElectionSupportThread(latch, failureCounter);
        }

        assertEquals(0, failureCounter.get());

        if (!latch.await(10, TimeUnit.SECONDS)) {
            LOGGER.info("Waited for all threads to start, but timed out. We had {} failures.", failureCounter);
        }
    }

}