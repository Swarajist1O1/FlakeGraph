class DummyClass_177159 {
    @Test
    public void throttle3() throws Exception {
        final WebClient client = WebClient.of(serverRule.httpUri());
        final AggregatedHttpResponse response1 = client.get("/http-throttle3").aggregate().get();
        assertThat(response1.status()).isEqualTo(HttpStatus.OK);

        assertThat(response1.headers().contains(HttpHeaderNames.RETRY_AFTER)).isFalse();
        assertThat(response1.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-RateLimit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-RateLimit-Reset")).isFalse();

        final AggregatedHttpResponse response2 = client.get("/http-throttle3").aggregate().get();
        assertThat(response2.status()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

        assertThat(response2.headers().contains(HttpHeaderNames.RETRY_AFTER)).isTrue();
        final long retryAfter2 = Long.parseLong(response2.headers().get(HttpHeaderNames.RETRY_AFTER));
        assertThat(retryAfter2).isBetween(0L, 10L);
        assertThat(response2.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-RateLimit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-RateLimit-Reset")).isFalse();
    }

}