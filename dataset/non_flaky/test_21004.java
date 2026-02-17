class DummyClass_21004 {
    @Test
    public void memoryEstimatorTestLargeObjects() {
        long maxMemory = 10000;
        long start = System.currentTimeMillis();
        long period = 500l;
        long sizeOfObjects = 200;
        SampleObject o = new SampleObject();
        DownsampleMemoryEstimator memoryEstimator = new DownsampleMemoryEstimator(maxMemory, start, period);
        boolean shouldReturn = false;
        for (long x = 100; x <= 5000; x += 100) {
            long timestamp = start + x;
            o.setSizeInBytes(o.sizeInBytes() + sizeOfObjects);
            shouldReturn = memoryEstimator.shouldReturnBasedOnMemoryUsage(timestamp, o);
            if (memoryEstimator.isNewBucket()) {
                long memoryPercentageUsedCalculated = Math.round((double) o.sizeInBytes() / maxMemory * 100);
                long memoryPercentageUsedEstimate = Math.round(memoryEstimator.getMemoryUsedPercentage());
                long percentError = Math.round(Math.abs(memoryPercentageUsedCalculated - memoryPercentageUsedEstimate)
                        / memoryPercentageUsedCalculated * 100);
                assertTrue(percentError == 0);
                assertTrue(memoryEstimator.isHighVolumeBuckets());
            }

            if (shouldReturn) {
                o.setSizeInBytes(0);
                memoryEstimator.reset();
            }
        }
        assertTrue(shouldReturn);
    }

}