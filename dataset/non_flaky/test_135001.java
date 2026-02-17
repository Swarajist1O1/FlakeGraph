class DummyClass_135001 {
    @Test
    public void testEncodingWithGenericSuperclass() throws Exception {
        final byte[] payload = "hello".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/encodingGenerics/Stuart"));
        client.connect();
        client.send(new TextWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "hello Stuart".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}