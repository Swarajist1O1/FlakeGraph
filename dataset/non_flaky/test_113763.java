class DummyClass_113763 {
    @Test
    public void manyToOneRetryImmediately() {
        Mono<Integer> test = newThreeErrorFlux()
                .<Mono<Integer>>as(GrpcRetry.ManyToOne.retryImmediately(Flux::single));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}