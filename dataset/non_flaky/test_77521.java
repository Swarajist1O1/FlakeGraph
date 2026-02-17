class DummyClass_77521 {
    @Test
    public void returnsEnvironment() {
        final Environment environment = RULE.getEnvironment();
        assertThat(environment.getName()).isEqualTo("DropwizardTestApplication");
    }

}