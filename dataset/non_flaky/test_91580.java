class DummyClass_91580 {
    @Test
    public void test0() throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<?>> futures = Lists.newArrayList();

        futures.add(executorService.submit(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("hi");
            }

}