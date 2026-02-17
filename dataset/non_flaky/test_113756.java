class DummyClass_113756 {
    @Test
    public void oneToManyRetryWhen() {
        Flux<Integer> test = newThreeErrorMono()
                .<Flux<Integer>>as(GrpcRetry.OneToMany.retryWhen(Mono::flux, Retry.any().retryMax(4)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}