class DummyClass_77529 {
    @Test
    public void supportsConfigAttributeOverrides() {
        final String content = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/test")
            .request().get(String.class);

        assertThat(content).isEqualTo("A new way to say Hooray!");
    }

}