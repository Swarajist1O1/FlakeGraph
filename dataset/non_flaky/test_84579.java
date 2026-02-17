class DummyClass_84579 {
    @Test
    public void testOfferShuffle() throws InterruptedException {
        int testIterations = 10;
        final CountDownLatch latch = new CountDownLatch(testIterations);
        final AtomicInteger failureCounter = new AtomicInteger();
        List<Thread> threads = new ArrayList<>(testIterations);

        for (int i = 1; i <= testIterations; i++) {
            threads.add(runElectionSupportThread(latch, failureCounter, Math.min(i * 1200, 10000)));
        }

        if (!latch.await(60, TimeUnit.SECONDS)) {
            LOGGER.info("Waited for all threads to start, but timed out. We had {} failures.", failureCounter);
        }
    }

}