class DummyClass_113758 {
    @Test
    public void oneToManyRetryAfter() {
        Flux<Integer> test = newThreeErrorMono()
                .<Flux<Integer>>as(GrpcRetry.OneToMany.retryAfter(Mono::flux, Duration.ofMillis(10)));

        StepVerifier.create(test)
                .expectNext(0)
                .expectComplete()
                .verify(Duration.ofSeconds(1));
    }

}