class DummyClass_77525 {
    @Test
    public void clientSupportsPatchMethod() {
        assertThat(RULE.client().target("http://localhost:" + RULE.getLocalPort() + "/echoPatch")
            .request()
            .method("PATCH", Entity.text("Patch is working"), String.class))
            .contains("Patch is working");
    }

}