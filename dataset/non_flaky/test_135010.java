class DummyClass_135010 {
    @Test
    public void websocketFragmentationStressTestCase() throws Exception {

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final CountDownLatch done = new CountDownLatch(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; ++i) {
            sb.append("message ");
            sb.append(i);
        }
        String toSend = sb.toString();

        final Session session = defaultContainer.connectToServer(new Endpoint() {
            @Override
            public void onOpen(Session session, EndpointConfig config) {
                session.addMessageHandler(new MessageHandler.Partial<byte[]>() {
                    @Override
                    public void onMessage(byte[] bytes, boolean b) {
                        try {
                            out.write(bytes);
                        } catch (IOException e) {
                            e.printStackTrace();
                            done.countDown();
                        }
                        if (b) {
                            done.countDown();
                        }
                    }

}