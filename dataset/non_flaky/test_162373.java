class DummyClass_162373 {
    @Test(timeout = 5_000)
    public void timeoutWorks() throws Exception {
        Future<Void> lazyFuture = new LazyFuture<Void>() {
            @Override
            @SneakyThrows(InterruptedException.class)
            protected Void resolve() {
                TimeUnit.MINUTES.sleep(1);
                return null;
            }
        };

        assertThrows("Should timeout", TimeoutException.class, () -> lazyFuture.get(10, TimeUnit.MILLISECONDS));
        pass("timeout works");
    }

}