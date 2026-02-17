class DummyClass_38198 {
    @Test
    public void testWritePerf() throws ExecutionException, InterruptedException {
        final long startTime = System.currentTimeMillis();
        final Future<Pair<Long, Set<byte[]>>>
            f1 = submitWriteJob(0, BATCH_SIZE / 4),
            f2 = submitWriteJob(BATCH_SIZE / 4, BATCH_SIZE / 2),
            f3 = submitWriteJob(BATCH_SIZE / 2, 3 * BATCH_SIZE / 4),
            f4 = submitWriteJob(3 * BATCH_SIZE / 4, BATCH_SIZE);
        final long rawBytes = f1.get().lhSide
                              + f2.get().lhSide
                              + f3.get().lhSide
                              + f4.get().lhSide;
        final long elapsedTime = System.currentTimeMillis() - startTime;
        final double elapsedSeconds = elapsedTime / 1000.0;
        final double megs = rawBytes / (1024.0 * 1024.0);
        System.out.println("MB = " + megs);
        System.out.println("MB/s = " + (megs/elapsedSeconds));
    }

}