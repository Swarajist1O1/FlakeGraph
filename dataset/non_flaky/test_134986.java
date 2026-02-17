class DummyClass_134986 {
    @Test
    public void testLongTextMessage() throws Exception {

        final String SEC_WEBSOCKET_EXTENSIONS = "permessage-deflate; client_no_context_takeover; client_max_window_bits";
        List<WebSocketExtension> extensionsList = WebSocketExtension.parse(SEC_WEBSOCKET_EXTENSIONS);

        final WebSocketClientNegotiation negotiation = new WebSocketClientNegotiation(null, extensionsList);

        Set<ExtensionHandshake> extensionHandshakes = new HashSet<>();
        extensionHandshakes.add(new PerMessageDeflateHandshake(true));

        final WebSocketChannel clientChannel = WebSocketClient.connect(DefaultServer.getWorker(), null, DefaultServer.getBufferPool(), OptionMap.EMPTY, new URI(DefaultServer.getDefaultServerURL()), WebSocketVersion.V13, negotiation, extensionHandshakes).get();

        final LinkedBlockingDeque<String> resultQueue  = new LinkedBlockingDeque<>();

        clientChannel.getReceiveSetter().set(new AbstractReceiveListener() {
            @Override
            protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) throws IOException {
                String data = message.getData();
                // WebSocketLogger.ROOT_LOGGER.info("onFullTextMessage() - Client - Received: " + data.getBytes().length + " bytes.");
                resultQueue.addLast(data);
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
                resultQueue.add("FAILED " + error);
            }

        });
        clientChannel.resumeReceives();

        int LONG_MSG = 125 * 1024;
        StringBuilder longMsg = new StringBuilder(LONG_MSG);

        for (int i = 0; i < LONG_MSG; i++) {
            longMsg.append(Integer.toString(i).charAt(0));
        }

        String message = longMsg.toString();
        for(int j = 0; j < MSG_COUNT; ++ j) {

            WebSockets.sendTextBlocking(message, clientChannel);
            String res = resultQueue.poll(10, TimeUnit.SECONDS);
            Assert.assertEquals(message, res);
        }

        clientChannel.sendClose();

    }

}