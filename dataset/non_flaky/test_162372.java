class DummyClass_162372 {
    @Test
    public void testLazyness() throws Exception {
        AtomicInteger counter = new AtomicInteger();

        Future<Integer> lazyFuture = new LazyFuture<Integer>() {
            @Override
            protected Integer resolve() {
                return counter.incrementAndGet();
            }
        };

        assertEquals("No resolve() invocations before get()", 0, counter.get());
        assertEquals("get() call returns proper result", 1, lazyFuture.get());
        assertEquals("resolve() was called only once after single get() call", 1, counter.get());

        counter.incrementAndGet();
        assertEquals("result of resolve() must be cached", 1, lazyFuture.get());
    }

}