class DummyClass_77545 {
    @Test
    public void testDefaultJsonProcessingMapper() {
        assertThat(resourceTestRule.target("/person/blah/runtime-exception")
                .request()
                .post(Entity.json("{ \"he: \"ho\"}"))
                .readEntity(String.class))
                .isEqualTo("{\"code\":400,\"message\":\"Unable to process JSON\"}");
    }

}