class DummyClass_77510 {
    @Test
    public void runWithExplicitConfig() {
        Map<String, String> response = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/test")
            .request()
            .get(new GenericType<Map<String, String>>() {
            });
        assertThat(response).containsOnly(entry("message", "stuff!"));
    }

}