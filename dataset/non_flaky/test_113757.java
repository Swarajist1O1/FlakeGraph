class DummyClass_113757 {
    @Test
    public void oneToManyRetryImmediately() {
        Flux<Integer> test = newThreeErrorMono()
                .<Flux<Integer>>as(GrpcRetry.OneToMany.retryImmediately(Mono::flux));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}