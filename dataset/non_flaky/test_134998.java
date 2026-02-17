class DummyClass_134998 {
    @Test
    public void testImplicitIntegerConversion() throws Exception {
        final byte[] payload = "12".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/increment/2"));
        client.connect();
        client.send(new TextWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "14".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}