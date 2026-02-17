class DummyClass_134987 {
    @Test
    public void testExtensionsHeaders() throws Exception {


        final String SEC_WEBSOCKET_EXTENSIONS = "permessage-deflate; client_no_context_takeover; client_max_window_bits";
        final String SEC_WEBSOCKET_EXTENSIONS_EXPECTED = "[permessage-deflate; client_no_context_takeover]";  // List format
        List<WebSocketExtension> extensions = WebSocketExtension.parse(SEC_WEBSOCKET_EXTENSIONS);

        final WebSocketClientNegotiation negotiation = new WebSocketClientNegotiation(null, extensions);

        Set<ExtensionHandshake> extensionHandshakes = new HashSet<>();
        extensionHandshakes.add(new PerMessageDeflateHandshake(true));

        final WebSocketChannel clientChannel = WebSocketClient.connect(DefaultServer.getWorker(), null, DefaultServer.getBufferPool(), OptionMap.EMPTY, new URI(DefaultServer.getDefaultServerURL()), WebSocketVersion.V13, negotiation, extensionHandshakes).get();

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicReference<String> result = new AtomicReference<>();

        clientChannel.getReceiveSetter().set(new AbstractReceiveListener() {
            @Override
            protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) throws IOException {
                String data = message.getData();
                WebSocketLogger.ROOT_LOGGER.info("onFullTextMessage - Client - Received: " + data.getBytes().length + " bytes . Data: " + data);
                result.set(data);
                latch.countDown();
            }

            @Override
            protected void onFullCloseMessage(WebSocketChannel channel, BufferedBinaryMessage message) throws IOException {
                message.getData().close();
                WebSocketLogger.ROOT_LOGGER.info("onFullCloseMessage");
            }

            @Override
            protected void onError(WebSocketChannel channel, Throwable error) {
                WebSocketLogger.ROOT_LOGGER.info("onError");
                super.onError(channel, error);
                error.printStackTrace();
                latch.countDown();
            }

        });
        clientChannel.resumeReceives();

        StreamSinkFrameChannel sendChannel = clientChannel.send(WebSocketFrameType.TEXT);
        new StringWriteChannelListener("Hello, World!").setup(sendChannel);

        latch.await(10, TimeUnit.SECONDS);
        Assert.assertEquals("Hello, World!", result.get());
        clientChannel.sendClose();

        Assert.assertEquals(SEC_WEBSOCKET_EXTENSIONS_EXPECTED, debug.getResponseExtensions().toString());
    }

}