class DummyClass_77535 {
    @Test
    public void testExceptionMapper() {
        final Response resp = resourceTestRule.target("test").request()
                .post(Entity.json(""));
        assertThat(resp.getStatus()).isEqualTo(500);
        assertThat(resp.readEntity(String.class)).isEqualTo("Can't touch this");
    }

}