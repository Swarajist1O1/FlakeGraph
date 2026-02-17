class DummyClass_77527 {
    @Test
    public void shouldGetStringBodyFromDropWizard() throws IOException {
        final URL url = new URL(RULE_WITH_INSTANCE.baseUri() + "/test");
        assertThat("foo").isEqualTo(Resources.toString(url, StandardCharsets.UTF_8));
    }

}