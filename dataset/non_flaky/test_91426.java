class DummyClass_91426 {
    @TestLogging("org.elasticsearch.xpack.ml.job.process.autodetect:DEBUG")
    public void testCanCloseClosingJob() throws Exception {
        AutodetectCommunicator communicator = mock(AutodetectCommunicator.class);
        AtomicInteger numberOfCommunicatorCloses = new AtomicInteger(0);
        doAnswer(invocationOnMock -> {
            numberOfCommunicatorCloses.incrementAndGet();
            // This increases the chance of the two threads both getting into
            // the middle of the AutodetectProcessManager.close() method
            Thread.yield();
            return null;
        }).when(communicator).close(anyBoolean(), anyString());
        AutodetectProcessManager manager = createManager(communicator);
        assertEquals(0, manager.numberOfOpenJobs());

        JobTask jobTask = mock(JobTask.class);
        when(jobTask.getJobId()).thenReturn("foo");
        manager.openJob(jobTask, e -> {});
        manager.processData(jobTask, analysisRegistry, createInputStream(""), randomFrom(XContentType.values()),
                mock(DataLoadParams.class), (dataCounts1, e) -> {});

        assertEquals(1, manager.numberOfOpenJobs());

        // Close the job in a separate thread
        Thread closeThread = new Thread(() -> manager.closeJob(jobTask, false, "in separate thread"));
        closeThread.start();
        Thread.yield();

        // Also close the job in the current thread, so that we have two simultaneous close requests
        manager.closeJob(jobTask, false, "in main test thread");

        // The 10 second timeout here is usually far in excess of what is required.  In the vast
        // majority of cases the other thread will exit within a few milliseconds.  However, it
        // has been observed that on some VMs the test can fail because the VM stalls at the
        // wrong moment.  A 10 second timeout is on a par with the length of time assertBusy()
        // would wait under these circumstances.
        closeThread.join(10000);
        assertFalse(closeThread.isAlive());

        // Only one of the threads should have called AutodetectCommunicator.close()
        assertEquals(1, numberOfCommunicatorCloses.get());
        assertEquals(0, manager.numberOfOpenJobs());
    }

}