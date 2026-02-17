class DummyClass_177156 {
    @Test
    public void serve1() throws Exception {
        final WebClient client = WebClient.of(serverRule.httpUri());
        final AggregatedHttpResponse response = client.get("/http-serve").aggregate().get();
        assertThat(response.status()).isEqualTo(HttpStatus.OK);

        assertThat(response.headers().contains(HttpHeaderNames.RETRY_AFTER)).isFalse();
        assertThat(response.headers().contains("RateLimit-Remaining")).isFalse();
        assertThat(response.headers().contains("X-RateLimit-Remaining")).isFalse();
        assertThat(response.headers().contains("X-Rate-Limit-Remaining")).isFalse();
        assertThat(response.headers().contains("RateLimit-Reset")).isFalse();
        assertThat(response.headers().contains("X-RateLimit-Reset")).isFalse();
        assertThat(response.headers().contains("X-Rate-Limit-Reset")).isFalse();
        assertThat(response.headers().contains("RateLimit-Limit")).isFalse();
        assertThat(response.headers().contains("X-RateLimit-Limit")).isFalse();
        assertThat(response.headers().contains("X-Rate-Limit-Limit")).isFalse();
    }

}