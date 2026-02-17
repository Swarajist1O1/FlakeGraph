class DummyClass_26201 {
    @Test
    public void testGetMultipleLocks() throws LockException
    {
        String otherResource = "RepairResource-b2e33e60-7af6-11e9-8f9e-2a86e4085a59-1";

        DistributedLock expectedLock = doReturnLockOnGetLock(RESOURCE);
        DistributedLock expectedOtherLock = doReturnLockOnGetLock(otherResource);

        assertGetLockRetrievesExpectedLock(RESOURCE, expectedLock);
        assertGetLockRetrievesExpectedLock(otherResource, expectedOtherLock);
    }

}