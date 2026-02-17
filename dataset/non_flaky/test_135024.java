class DummyClass_135024 {
    @Test
    public void testSimpleBasic() throws Exception {
        //
        final UndertowClient client = createClient();

        final List<ClientResponse> responses = new CopyOnWriteArrayList<>();
        final CountDownLatch latch = new CountDownLatch(10);
        final ClientConnection connection = client.connect(ADDRESS, worker, new UndertowXnioSsl(worker.getXnio(), OptionMap.EMPTY, DefaultServer.getClientSSLContext()), DefaultServer.getBufferPool(), OptionMap.create(UndertowOptions.ENABLE_HTTP2, true)).get();
        try {
            connection.getIoThread().execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        final ClientRequest request = new ClientRequest().setMethod(Methods.GET).setPath(MESSAGE);
                        request.getRequestHeaders().put(Headers.HOST, DefaultServer.getHostAddress());
                        connection.sendRequest(request, createClientCallback(responses, latch));
                    }
                }

}