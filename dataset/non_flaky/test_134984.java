class DummyClass_134984 {
    @Test
    public void testConnectionClosedOnPause() throws Exception {
        final CountDownLatch done = new CountDownLatch(1);
        final AtomicReference<String> message = new AtomicReference<>();
        WebSocketChannel channel = WebSocketClient.connectionBuilder(DefaultServer.getWorker(), DefaultServer.getBufferPool(), new URI(DefaultServer.getDefaultServerURL() + "/"))
                .connect().get();
        channel.getReceiveSetter().set(new ChannelListener<WebSocketChannel>() {
            @Override
            public void handleEvent(WebSocketChannel channel) {
                try {
                    StreamSourceFrameChannel res = channel.receive();
                    if(res == null) {
                        return;
                    }
                    if (res.getType() == WebSocketFrameType.CLOSE) {
                        message.set("closed");
                        done.countDown();
                    }
                    Channels.drain(res, Long.MAX_VALUE);
                } catch (IOException e) {
                    if(message.get() == null) {
                        e.printStackTrace();
                        message.set("error");
                        done.countDown();
                    }
                }
            }

}