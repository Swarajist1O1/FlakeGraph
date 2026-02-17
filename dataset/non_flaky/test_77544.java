class DummyClass_77544 {
    @Test
    public void testDefaultConstraintViolation() {
        assertThat(resourceTestRule.target("/person/blah/index")
                .queryParam("ind", -1).request()
                .get().readEntity(String.class))
                .isEqualTo("{\"errors\":[\"query param ind must be greater than or equal to 0\"]}");
    }

}