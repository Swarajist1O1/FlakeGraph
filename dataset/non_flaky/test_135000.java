class DummyClass_135000 {
    @Test
    public void testEncodingAndDecodingBinary() throws Exception {
        final byte[] payload = "hello".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/encoding/Stuart"));
        client.connect();
        client.send(new BinaryWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "hello Stuart".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}