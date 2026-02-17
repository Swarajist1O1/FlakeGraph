class DummyClass_26179 {
    @Test
    public void testCloseAllLocks()
    {
        List<DummyLock> locks = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            locks.add(new DummyLock());
        }

        new LockCollection(locks).close();

        for (DummyLock lock : locks)
        {
            assertThat(lock.closed).isTrue();
        }
    }

}