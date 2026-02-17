class DummyClass_135015 {
    @Test
    public void testSendPing() throws Exception {
        //
        final UndertowClient client = createClient();

        final List<ClientResponse> responses = new CopyOnWriteArrayList<>();
        final FutureResult<Boolean> result = new FutureResult<>();
        final CountDownLatch latch = new CountDownLatch(3);
        final ClientConnection connection = client.connect(ADDRESS, worker, DefaultServer.getBufferPool(), OptionMap.EMPTY).get();
        Assert.assertTrue(connection.isPingSupported());
        try {
            connection.getIoThread().execute(new Runnable() {
                @Override
                public void run() {
                        final ClientRequest request = new ClientRequest().setMethod(Methods.GET).setPath(MESSAGE);
                        request.getRequestHeaders().put(Headers.HOST, DefaultServer.getHostAddress());
                        connection.sendRequest(request, createClientCallback(responses, latch));
                        connection.sendPing(new ClientConnection.PingListener() {
                            @Override
                            public void acknowledged() {
                                result.setResult(true);
                                latch.countDown();
                            }

}