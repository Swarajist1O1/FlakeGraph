class DummyClass_177209 {
    @Test
    public void proxyWithTrailers() throws Throwable {
        final WebClient client = WebClient.of(frontendServer.httpUri());

        final AtomicBoolean headersReceived = new AtomicBoolean();
        final AtomicBoolean complete = new AtomicBoolean();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        client.get("/trailers").subscribe(new Subscriber<HttpObject>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

}