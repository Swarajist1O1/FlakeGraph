class DummyClass_26197 {
    @Test
    public void testThreeTasksOneThrowing() throws LockException
    {
        ShortRunningMultipleTasks job = new ShortRunningMultipleTasks(ScheduledJob.Priority.LOW, 3);
        myScheduler.schedule(job);

        when(myLockFactory.tryLock(any(), anyString(), anyInt(), anyMap()))
                .thenReturn(new DummyLock())
                .thenThrow(new LockException(""))
                .thenReturn(new DummyLock());

        myScheduler.run();

        assertThat(job.getNumRuns()).isEqualTo(2);
        assertThat(myScheduler.getQueueSize()).isEqualTo(1);
        verify(myLockFactory, times(3)).tryLock(any(), anyString(), anyInt(), anyMap());
    }

}