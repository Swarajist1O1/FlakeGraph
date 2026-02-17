class DummyClass_113764 {
    @Test
    public void manyToOneRetryAfter() {
        Mono<Integer> test = newThreeErrorFlux()
                .<Mono<Integer>>as(GrpcRetry.ManyToOne.retryAfter(Flux::single, Duration.ofMillis(10)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}