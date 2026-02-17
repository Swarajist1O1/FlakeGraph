class DummyClass_135007 {
    @Test
    public void testDynamicAnnotatedEndpoint() throws Exception {
        final byte[] payload = "hello".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/dynamicEchoEndpoint?annotated=true"));
        client.connect();
        client.send(new TextWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "opened:true /dynamicEchoEndpoint hello".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}