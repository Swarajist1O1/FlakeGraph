class DummyClass_26191 {
    @Test
    public void testRunningTwoTasksStoppedAfterFirstByPolicy() throws LockException
    {
        ShortRunningMultipleTasks job = new ShortRunningMultipleTasks(ScheduledJob.Priority.LOW, 2, () -> {
            when(myRunPolicy.validate(any(ScheduledJob.class))).thenReturn(1L);
        });
        myScheduler.schedule(job);

        when(myLockFactory.tryLock(any(), anyString(), anyInt(), anyMap())).thenReturn(new DummyLock());

        myScheduler.run();

        assertThat(job.getNumRuns()).isEqualTo(1);
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
        verify(myLockFactory).tryLock(any(), anyString(), anyInt(), anyMap());
    }

}