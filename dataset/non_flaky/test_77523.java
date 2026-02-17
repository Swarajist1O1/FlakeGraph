class DummyClass_77523 {
    @Test
    public void canPerformAdminTaskWithPostBody() {
        final String response
            = RULE.client().target("http://localhost:"
            + RULE.getAdminPort() + "/tasks/echo")
            .request()
            .post(Entity.entity("Custom message", MediaType.TEXT_PLAIN), String.class);

        assertThat(response).isEqualTo("Custom message");
    }

}