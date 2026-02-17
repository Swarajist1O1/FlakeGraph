class DummyClass_113754 {
    @Test
    public void noRetryMakesErrorFlowabable() {
        Flux<Integer> test = newThreeErrorFlux()
                .as(flux -> flux);

        StepVerifier.create(test)
                .expectErrorMessage("Not yet!")
                .verify(Duration.ofSeconds(1));
    }

}