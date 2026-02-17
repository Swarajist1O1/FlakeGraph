class DummyClass_26202 {
    @Test
    public void testGetOtherLockAfterThrowingOnAnotherResource() throws LockException
    {
        String otherResource = "RepairResource-b2e33e60-7af6-11e9-8f9e-2a86e4085a59-1";

        LockException expectedException = doThrowOnGetLock(RESOURCE);
        DistributedLock expectedOtherLock = doReturnLockOnGetLock(otherResource);

        assertGetLockThrowsException(RESOURCE, expectedException);
        assertGetLockRetrievesExpectedLock(otherResource, expectedOtherLock);
    }

}