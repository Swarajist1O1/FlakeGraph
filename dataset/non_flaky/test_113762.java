class DummyClass_113762 {
    @Test
    public void manyToOneRetryWhen() {
        Mono<Integer> test = newThreeErrorFlux()
                .<Mono<Integer>>as(GrpcRetry.ManyToOne.retryWhen(Flux::single, Retry.any().retryMax(4)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}