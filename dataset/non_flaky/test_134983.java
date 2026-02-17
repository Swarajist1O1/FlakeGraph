class DummyClass_134983 {
    @Test
    public void testConnectionWaitsForMessageEnd() throws Exception {
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicReference<String> message = new AtomicReference<>();
        WebSocketChannel channel = WebSocketClient.connectionBuilder(DefaultServer.getWorker(), DefaultServer.getBufferPool(), new URI(DefaultServer.getDefaultServerURL() + "/"))
                .connect().get();
        channel.getReceiveSetter().set(new AbstractReceiveListener() {
            @Override
            protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage msg) throws IOException {
                message.set(msg.getData());
                done.countDown();
            }

            @Override
            protected void onError(WebSocketChannel channel, Throwable error) {
                error.printStackTrace();
                message.set("error");
                done.countDown();
            }

            @Override
            protected void onFullCloseMessage(WebSocketChannel channel, BufferedBinaryMessage message) throws IOException {
                message.getData().free();
                done.countDown();
            }
        });
        channel.resumeReceives();
        Assert.assertTrue(channel.isOpen());
        WebSockets.sendText("Hello World", channel, null);
        Thread.sleep(500);
        serverContainer.pause(null);
        try {
            Assert.assertTrue(done.await(10, TimeUnit.SECONDS));
            Assert.assertEquals("Hello World", message.get());
        } finally {
            serverContainer.resume();
        }
    }

}