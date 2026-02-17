class DummyClass_135008 {
    @Test
    public void testDynamicProgramaticEndpoint() throws Exception {
        final byte[] payload = "hello".getBytes();
        final FutureResult latch = new FutureResult();

        WebSocketTestClient client = new WebSocketTestClient(WebSocketVersion.V13, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/dynamicEchoEndpoint"));
        client.connect();
        client.send(new TextWebSocketFrame(Unpooled.wrappedBuffer(payload)), new FrameChecker(TextWebSocketFrame.class, "/dynamicEchoEndpoint hello".getBytes(), latch));
        latch.getIoFuture().get();
        client.destroy();
    }

}