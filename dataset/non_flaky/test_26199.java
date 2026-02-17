class DummyClass_26199 {
    @Test
    public void testGetLock() throws LockException
    {
        DistributedLock expectedLock = doReturnLockOnGetLock();

        assertGetLockRetrievesExpectedLock(expectedLock);
    }

}