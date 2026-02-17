class DummyClass_135776 {
    /*@Test
    public void testRequestContextViaPluginInTimeout() {
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixConcurrencyStrategy() {
            @Override
            public <T> Callable<T> wrapCallable(final Callable<T> callable) {
                return new RequestIdCallable<T>(callable);
            }
        });

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        testRequestIdThreadLocal.set("foobar");
        final AtomicReference<String> valueInTimeout = new AtomicReference<String>();

        new DummyCommand().toObservable()
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        System.out.println("initialized = " + HystrixRequestContext.isCurrentThreadInitialized());
                        System.out.println("requestId (timeout) = " + testRequestIdThreadLocal.get());
                        valueInTimeout.set(testRequestIdThreadLocal.get());
                    }

}