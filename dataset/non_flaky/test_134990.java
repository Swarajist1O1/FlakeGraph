class DummyClass_134990 {
    @Test
    public void testWebSocketInRootContext() throws Exception {
        final byte[] payload = "hello".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws"));
        client.connect();
        client.send(new TextWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "hello".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}