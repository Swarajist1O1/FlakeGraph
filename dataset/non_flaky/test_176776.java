class DummyClass_176776 {
    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenAlreadyUsed() throws Exception {
        // simulate cucumber scenario start
        simulateCucumberScenarioStart();

        instance.addModule(binder -> {
        });
    }

}