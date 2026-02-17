class DummyClass_77547 {
    @Test
    public void testValidationGroupsException() {
        final Response resp = resourceTestRule.target("/person/blah/validation-groups-exception")
                .request()
                .post(Entity.json("{}"));
        assertThat(resp.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        assertThat(resp.readEntity(String.class))
                .isEqualTo("{\"code\":500,\"message\":\"Parameters must have the same" +
                        " validation groups in validationGroupsException\"}");
    }

}