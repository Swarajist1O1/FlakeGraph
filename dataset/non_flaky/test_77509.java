class DummyClass_77509 {
    @Test
    public void runWithoutConfigFile() {
        Map<String, String> response = RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/test")
            .request()
            .get(new GenericType<Map<String, String>>() {
            });
        assertThat(response).containsOnly(entry("color", "orange"));
    }

}