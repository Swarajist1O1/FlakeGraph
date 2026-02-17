class DummyClass_77522 {
    @Test
    public void canPerformAdminTask() {
        final String response
            = RULE.client().target("http://localhost:"
            + RULE.getAdminPort() + "/tasks/hello?name=test_user")
            .request()
            .post(Entity.entity("", MediaType.TEXT_PLAIN), String.class);

        assertThat(response).isEqualTo("Hello has been said to test_user");
    }

}