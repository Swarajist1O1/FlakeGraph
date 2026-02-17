class DummyClass_177164 {
    @Test
    public void shouldBeAcceptedBecauseOfLeeway() throws InterruptedException, UnsupportedEncodingException {
        final SamlRequestIdManager manager =
                SamlRequestIdManager.ofJwt("me", "test", 1, 1);

        final Instant started = Instant.now();
        final String id = manager.newId();
        assertThat(manager.validateId(id)).isTrue();

        await().pollDelay(Durations.TWO_HUNDRED_MILLISECONDS)
               .atMost(Durations.FIVE_SECONDS)
               .untilAsserted(() -> assertThat(manager.validateId(id)).isFalse());

        assertThat(java.time.Duration.between(started, Instant.now()).toMillis())
                .isGreaterThan(TimeUnit.SECONDS.toMillis(2));
    }

}