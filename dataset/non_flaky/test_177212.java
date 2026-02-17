class DummyClass_177212 {
    @Test
    public void testExtractHost() {
        // additionalRequestHeaders has the highest precedence.
        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, "foo")),
                               HttpRequest.of(RequestHeaders.of(HttpMethod.GET, "/",
                                                                HttpHeaderNames.AUTHORITY, "bar:8080")),
                               Endpoint.of("baz", 8080))).isEqualTo("foo");

        // Request header
        assertThat(extractHost(context(HttpHeaders.of()),
                               HttpRequest.of(RequestHeaders.of(HttpMethod.GET, "/",
                                                                HttpHeaderNames.AUTHORITY, "bar:8080")),
                               Endpoint.of("baz", 8080))).isEqualTo("bar");

        // Endpoint.host() has the lowest precedence.
        assertThat(extractHost(context(HttpHeaders.of()),
                               HttpRequest.of(HttpMethod.GET, "/"),
                               Endpoint.of("baz", 8080))).isEqualTo("baz");

        // IPv6 address authority
        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, "[::1]:8443")),
                               HttpRequest.of(HttpMethod.GET, "/"),
                               Endpoint.of("baz", 8080))).isEqualTo("::1");

        // An invalid authority should be ignored.
        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, "[::1")),
                               HttpRequest.of(HttpMethod.GET, "/"),
                               Endpoint.of("baz", 8080))).isEqualTo("baz");

        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, ":8080")),
                               HttpRequest.of(HttpMethod.GET, "/"),
                               Endpoint.of("baz", 8080))).isEqualTo("baz");

        // If additionalRequestHeader's authority is invalid but req.authority() is valid,
        // use the authority from 'req'.
        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, "[::1")),
                               HttpRequest.of(RequestHeaders.of(HttpMethod.GET, "/",
                                                                HttpHeaderNames.AUTHORITY, "bar")),
                               Endpoint.of("baz", 8080))).isEqualTo("bar");

        assertThat(extractHost(context(HttpHeaders.of(HttpHeaderNames.AUTHORITY, ":8080")),
                               HttpRequest.of(RequestHeaders.of(HttpMethod.GET, "/",
                                                                HttpHeaderNames.AUTHORITY, "bar")),
                               Endpoint.of("baz", 8080))).isEqualTo("bar");
    }

}