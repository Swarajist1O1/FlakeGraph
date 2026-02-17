class DummyClass_26203 {
    @Test
    public void testGetLockAfterCachedExceptionHasExpired() throws LockException, InterruptedException
    {
        myLockCache = new LockCache(mockedLockSupplier, 20, TimeUnit.MILLISECONDS);

        LockException expectedException = doThrowOnGetLock();
        assertGetLockThrowsException(expectedException);

        Thread.sleep(20);

        DistributedLock expectedLock = doReturnLockOnGetLock();
        assertGetLockRetrievesExpectedLock(expectedLock);
    }

}