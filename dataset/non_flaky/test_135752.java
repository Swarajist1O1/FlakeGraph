class DummyClass_135752 {
    @Test
    public void testAddCallback() throws Exception {

        final DynamicStringProperty node1 = new DynamicStringProperty("n1", "n1");
        final HystrixPropertiesChainedArchaiusProperty.StringProperty node2 = new HystrixPropertiesChainedArchaiusProperty.StringProperty("n2", node1);

        final AtomicInteger callbackCount = new AtomicInteger(0);

        node2.addCallback(new Runnable() {
            @Override
            public void run() {
                callbackCount.incrementAndGet();
            }

}