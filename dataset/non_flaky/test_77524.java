class DummyClass_77524 {
    @Test
    public void clientUsesJacksonMapperFromEnvironment() {
        assertThat(RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/message")
            .request()
            .get(DropwizardTestApplication.MessageView.class).getMessage())
            .contains("Yes, it's here");
    }

}