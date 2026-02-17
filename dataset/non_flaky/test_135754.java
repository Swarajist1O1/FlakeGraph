class DummyClass_135754 {
    @Test
    public void testThreadContextOnTimeout() {
        final AtomicBoolean isInitialized = new AtomicBoolean();
        new TimeoutCommand().toObservable()
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        isInitialized.set(HystrixRequestContext.isCurrentThreadInitialized());
                    }

}