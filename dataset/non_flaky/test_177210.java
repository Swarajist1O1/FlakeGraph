class DummyClass_177210 {
    @Test
    public void proxyWithTrailersOnly() throws Throwable {
        final WebClient client = WebClient.of(frontendServer.httpUri());

        final AtomicBoolean complete = new AtomicBoolean();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        client.get("/trailers-only").subscribe(new Subscriber<HttpObject>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

}