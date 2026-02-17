class DummyClass_77518 {
    @Test
    public void canGetExpectedResourceOverHttp() {
        final String content = ClientBuilder.newClient().target(
            "http://localhost:" + RULE.getLocalPort() + "/test").request().get(String.class);

        assertThat(content).isEqualTo("Yes, it's here");
    }

}