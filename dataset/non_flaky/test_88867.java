class DummyClass_88867 {
    @Test
    public void testComputeDataIfAbsent() {
        AtomicLong cnt = new AtomicLong();

        for (int i = 0; i < 10; i++) {
            Integer res = (Integer) dataStorage.computeDataIfAbsent(0, () -> {
                cnt.incrementAndGet();

                return 42;
            });

            assertEquals(42, res.intValue());
        }

        assertEquals(1, cnt.intValue());
    }

}