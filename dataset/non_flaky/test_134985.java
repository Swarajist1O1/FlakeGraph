class DummyClass_134985 {
    @Test
    public void testRejectWhenSuspended() throws Exception {
        try {
            serverContainer.pause(null);
            WebSocketChannel channel = WebSocketClient.connectionBuilder(DefaultServer.getWorker(), DefaultServer.getBufferPool(), new URI(DefaultServer.getDefaultServerURL() + "/"))
                    .connect().get();
            IoUtils.safeClose(channel);
            Assert.fail();
        } catch (UpgradeFailedException e) {
            //expected
        } finally {
            serverContainer.resume();
        }

    }

}