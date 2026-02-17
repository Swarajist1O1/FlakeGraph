class DummyClass_26188 {
    @Test
    public void testRunningNoJobs() throws LockException
    {
        myScheduler.run();

        verify(myLockFactory, never()).tryLock(any(), anyString(), anyInt(), anyMap());
    }

}