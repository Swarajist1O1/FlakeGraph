class DummyClass_177168 {
    @Test
    public void shouldBeAlreadyAuthenticated() throws Exception {
        final RequestHeaders req = RequestHeaders.of(HttpMethod.GET, "/redirect",
                                                     HttpHeaderNames.COOKIE, "test=test");
        final AggregatedHttpResponse resp = client.execute(req).aggregate().join();
        assertThat(resp.status()).isEqualTo(HttpStatus.OK);
        assertThat(resp.contentUtf8()).isEqualTo("authenticated");
    }

}