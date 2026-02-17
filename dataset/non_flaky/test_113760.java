class DummyClass_113760 {
    @Test
    public void manyToManyRetryImmediately() {
        Flux<Integer> test = newThreeErrorFlux()
                .<Integer>compose(GrpcRetry.ManyToMany.retryImmediately(Function.identity()));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}