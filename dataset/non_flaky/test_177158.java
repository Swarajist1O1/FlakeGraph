class DummyClass_177158 {
    @Test
    public void throttle2() throws Exception {
        final WebClient client = WebClient.of(serverRule.httpUri());
        final AggregatedHttpResponse response1 = client.get("/http-throttle2").aggregate().get();
        assertThat(response1.status()).isEqualTo(HttpStatus.OK);

        assertThat(response1.headers().contains(HttpHeaderNames.RETRY_AFTER)).isFalse();
        assertThat(response1.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response1.headers().contains("X-RateLimit-Remaining", "0")).isTrue();
        assertThat(response1.headers().contains("X-RateLimit-Reset")).isTrue();
        final long reset1 = Long.parseLong(response1.headers().get("X-RateLimit-Reset"));
        assertThat(reset1).isBetween(0L, 10L);
        assertThat(response1.headers().get("X-RateLimit-Limit")).isEqualTo("1, 1;window=10");

        final AggregatedHttpResponse response2 = client.get("/http-throttle2").aggregate().get();
        assertThat(response2.status()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

        assertThat(response2.headers().contains(HttpHeaderNames.RETRY_AFTER, "15")).isTrue();
        assertThat(response2.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response2.headers().contains("X-RateLimit-Remaining", "0")).isTrue();
        assertThat(response2.headers().contains("X-RateLimit-Reset", "15")).isTrue();
        assertThat(response1.headers().get("X-RateLimit-Limit")).isEqualTo("1, 1;window=10");
    }

}