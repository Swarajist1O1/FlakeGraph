class DummyClass_177157 {
    @Test
    public void throttle1() throws Exception {
        final WebClient client = WebClient.of(serverRule.httpUri());
        final AggregatedHttpResponse response1 = client.get("/http-throttle1").aggregate().get();
        assertThat(response1.status()).isEqualTo(HttpStatus.OK);

        assertThat(response1.headers().contains(HttpHeaderNames.RETRY_AFTER)).isFalse();
        assertThat(response1.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-RateLimit-Remaining", "0")).isTrue();
        assertThat(response1.headers().contains("X-RateLimit-Reset")).isTrue();
        final long reset1 = Long.parseLong(response1.headers().get("X-RateLimit-Reset"));
        assertThat(reset1).isBetween(0L, 10L);
        assertThat(response1.headers().contains("X-RateLimit-Limit")).isFalse();

        final AggregatedHttpResponse response2 = client.get("/http-throttle1").aggregate().get();
        assertThat(response2.status()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

        assertThat(response2.headers().contains(HttpHeaderNames.RETRY_AFTER)).isTrue();
        final long retryAfter2 = Long.parseLong(response2.headers().get(HttpHeaderNames.RETRY_AFTER));
        assertThat(retryAfter2).isBetween(0L, 10L);
        assertThat(response2.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-RateLimit-Remaining", "0")).isTrue();
        assertThat(response2.headers().contains("X-RateLimit-Reset")).isTrue();
        final long reset = Long.parseLong(response2.headers().get("X-RateLimit-Reset"));
        assertThat(reset).isEqualTo(retryAfter2);
        assertThat(response2.headers().contains("X-RateLimit-Limit")).isFalse();
    }

}