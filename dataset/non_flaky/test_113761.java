class DummyClass_113761 {
    @Test
    public void manyToManyRetryAfter() {
        Flux<Integer> test = newThreeErrorFlux()
                .<Integer>compose(GrpcRetry.ManyToMany.retryAfter(Function.identity(), Duration.ofMillis(10)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}