class DummyClass_113755 {
    @Test
    public void noRetryMakesErrorSingle() {
        Mono<Integer> test = newThreeErrorMono()
                .as(mono -> mono);

        StepVerifier.create(test)
                .expectErrorMessage("Not yet!")
                .verify(Duration.ofSeconds(1));
    }

}