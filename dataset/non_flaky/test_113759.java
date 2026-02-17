class DummyClass_113759 {
    @Test
    public void manyToManyRetryWhen() {
        Flux<Integer> test = newThreeErrorFlux()
                .<Integer>compose(GrpcRetry.ManyToMany.retryWhen(Function.identity(), Retry.any().retryMax(4)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}