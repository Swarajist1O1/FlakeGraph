class DummyClass_135009 {
    @Test
    public void webSocketStringStressTestCase() throws Exception {
        List<CountDownLatch> latches = new ArrayList<>();
        for (int i = 0; i < NUM_THREADS; ++i) {
            final CountDownLatch latch = new CountDownLatch(1);
            latches.add(latch);
            final Session session = deployment.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig config) {
                }

}