class DummyClass_26200 {
    @Test
    public void testGetThrowingLockIsCached() throws LockException
    {
        LockException expectedExcetion = doThrowOnGetLock();

        assertGetLockThrowsException(expectedExcetion);

        // Reset return type, locking should still throw
        doReturnLockOnGetLock();

        assertGetLockThrowsException(expectedExcetion);
    }

}