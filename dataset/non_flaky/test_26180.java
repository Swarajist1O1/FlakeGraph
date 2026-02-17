class DummyClass_26180 {
    @Test
    public void testCloseAllLocksOneThrowing()
    {
        List<DistributedLock> locks = new ArrayList<>();
        for (int i = 0; i < 4; i++)
        {
            locks.add(new DummyLock());
        }

        locks.add(new ThrowingLock());

        for (int i = 0; i < 5; i++)
        {
            locks.add(new DummyLock());
        }

        new LockCollection(locks).close();

        for (DistributedLock lock : locks)
        {
            if (lock instanceof DummyLock)
            {
                assertThat(((DummyLock) lock).closed).isTrue();
            }
        }
    }

}