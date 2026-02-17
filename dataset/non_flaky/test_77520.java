class DummyClass_77520 {
    @Test
    public void returnsApplication() {
        final DropwizardTestApplication application = RULE.getApplication();
        assertThat(application).isNotNull();
    }

}